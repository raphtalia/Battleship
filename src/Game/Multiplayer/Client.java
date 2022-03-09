package Game.Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(String address, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(address, port);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void close() throws IOException {
        clientSocket.close();
    }

    public String read() throws IOException {
        return in.readUTF();
    }

    public Client write(String str) throws IOException {
        out.writeUTF(str);
        return this;
    }
}
