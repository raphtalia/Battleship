import java.io.IOException;
import java.net.UnknownHostException;

import CLI.Console;
import Game.Multiplayer.Server;

public class ServerMain {
    public static void main(String[] args) throws UnknownHostException, IOException {
        final Console console = new Console();

        final String ip = console.getString("IP address: ", "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$",
                "Invalid IP address (A.B.C.D)");
        final int port = console.getInt("Port: ", 0, 65535);
        final Server server = new Server();

        System.out.println("Waiting for players...");
        server.connect(ip, port);
        System.out.println("Player 1 connected, waiting for player 2...");
        server.connect(ip, port);
        System.out.println("All players connected");
        System.out.println(server.readAny());
        server.sendAll("hello from server!");
    }
}