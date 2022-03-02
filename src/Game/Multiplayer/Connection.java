package Game.Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Connection {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Connection(ConnectionType type, String address, int port) throws UnknownHostException, IOException {
        switch (type) {
            case SERVER:
                socket = new ServerSocket(port, 50, InetAddress.getByName(address)).accept();
                break;
            case CLIENT:
                socket = new Socket(address, port);
                break;
        }

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    public String read() throws IOException {
        return in.readUTF();
    }

    public Connection write(String str) throws IOException {
        out.writeUTF(str);
        return this;
    }
}
