package Game.Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketWrapper {
    private Socket socket;

    public final DataInputStream in;
    public final DataOutputStream out;

    public SocketWrapper(Socket baseSocket) throws IOException {
        socket = baseSocket;
        in = new DataInputStream(baseSocket.getInputStream());
        out = new DataOutputStream(baseSocket.getOutputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    public String read() throws IOException {
        return in.readUTF();
    }

    public SocketWrapper write(String str) throws IOException {
        out.writeUTF(str);
        return this;
    }
}
