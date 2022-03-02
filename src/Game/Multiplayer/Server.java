package Game.Multiplayer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.net.UnknownHostException;
import java.util.Vector;

public class Server {
    private Vector<Connection> connections;
    private DataInputStream in;

    public Server() {
        connections = new Vector<Connection>();
    }

    public Server connect(String address, int port) throws UnknownHostException, IOException {
        connections.add(new Connection(ConnectionType.SERVER, address, port));

        if (connections.size() == 1) {
            in = new DataInputStream(connections.get(0).in);
        } else {
            Vector<DataInputStream> inStreams = new Vector<DataInputStream>();
            for (Connection connection : connections) {
                inStreams.add(new DataInputStream(connection.in));
            }

            in = new DataInputStream(new SequenceInputStream(inStreams.elements()));
        }

        return this;
    }

    public Server closeAll(String address, int port) throws IOException {
        for (Connection connection : connections) {
            connection.close();
            connections.remove(connection);
            break;
        }
        return this;
    }

    public Server sendAll(String str) throws IOException {
        for (Connection connection : connections) {
            connection.write(str);
        }
        return this;
    }

    public String readAny() throws IOException {
        return in.readUTF();
    }
}
