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
        final Server server = new Server(ip, port);

        System.out.println("Waiting for players...");
        server.accept();
        System.out.println("Player 1 connected, waiting for player 2...");
        server.accept();
        System.out.println("All players connected");

        server.writeAll("hello from server!");
        while (true) {
            System.out.println(server.readAny());
        }
    }
}