package Game.Multiplayer;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
    private Connection connection;

    public Client(String address, int port) throws UnknownHostException, IOException {
        connection = new Connection(ConnectionType.CLIENT, address, port);
    }

    public void close() throws IOException {
        connection.close();
    }

    public String read() throws IOException {
        return connection.read();
    }

    public Client send(String str) throws IOException {
        connection.write(str);
        return this;
    }
}
