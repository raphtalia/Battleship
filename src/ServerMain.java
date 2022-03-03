import java.io.IOException;
import java.net.UnknownHostException;

import Game.Multiplayer.Server;

public class ServerMain {
    public static void main(String[] args) throws UnknownHostException, IOException {
        final Server server = new Server();
        server.connect("192.168.1.94", 3000);
        System.out.println(server.readAny());
        server.sendAll("hello from server!");
    }
}