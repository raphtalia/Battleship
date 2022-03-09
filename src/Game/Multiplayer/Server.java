package Game.Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket;
    private DataInputStream in;
    private Vector<SocketWrapper> connections = new Vector<SocketWrapper>();

    public Server(String address, int port) throws UnknownHostException, IOException {
        serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
    }

    public SocketWrapper accept() throws IOException {
        final SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());

        connections.add(socketWrapper);

        if (connections.size() == 1) {
            in = socketWrapper.in;
        } else {
            in = new DataInputStream(new SequenceInputStream(getInputStreams().elements()));
        }

        return socketWrapper;
    }

    public Vector<DataInputStream> getInputStreams() {
        Vector<DataInputStream> inStreams = new Vector<DataInputStream>();
        for (SocketWrapper connection : connections) {
            inStreams.add(new DataInputStream(connection.in));
        }
        return inStreams;
    }

    public Vector<DataOutputStream> getOutputStreams() {
        Vector<DataOutputStream> outStreams = new Vector<DataOutputStream>();
        for (SocketWrapper connection : connections) {
            outStreams.add(new DataOutputStream(connection.out));
        }
        return outStreams;
    }

    public Server closeAll(String address, int port) throws IOException {
        for (SocketWrapper connection : connections) {
            connection.close();
            connections.remove(connection);
        }
        return this;
    }

    public Server writeAll(String str) throws IOException {
        for (SocketWrapper connection : connections) {
            connection.write(str);
        }
        return this;
    }

    public String readAny() throws IOException {
        return in.readUTF();
    }
}
