import java.io.IOException;
import java.net.UnknownHostException;

import CLI.Console;
import Game.Multiplayer.Client;

public class ClientMain {
    private static int indexOfAlphabet(char c) {
        c = Character.toUpperCase(c);

        if (c < 'A' || c > 'Z') {
            throw new IllegalArgumentException("Invalid character: " + c);
        }

        return c - 'A';
    }

    private static char getChar(int i) {
        if (i < 0 || i > 25) {
            throw new IllegalArgumentException("Invalid index: " + i);
        }

        return (char) (i + 'A');
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        // System.out.println(new Colors("Battleship").bold().red());

        // final Player plyr = new Player(26, 26);

        // plyr.placeShipRandom(ShipType.CARRIER, 2);
        // plyr.placeShipRandom(ShipType.BATTLESHIP, 2);
        // plyr.placeShipRandom(ShipType.CRUISER, 2);
        // plyr.placeShipRandom(ShipType.SUBMARINE, 2);
        // plyr.placeShipRandom(ShipType.DESTROYER, 2);
        // plyr.placeShipRandom(ShipType.PATROL_BOAT, 2);

        // for (int i = 0; i < 400; i++) {
        // plyr.shootRandom();
        // }

        // plyr.printMap();

        // System.out.printf("%s out of %s ships remain", plyr.getRemainingShips(),
        // plyr.getTotalShips());

        final Console console = new Console();

        final String ip = console.getString("IP address: ", "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$",
                "Invalid IP address (A.B.C.D)");
        final int port = console.getInt("Port: ", 0, 65535);
        final Client client = new Client(ip, port);

        client.write("hello from client!" + Math.random());
        System.out.println(client.read());
        System.out.println(client.read());

        // System.out.println(console.getBoolean("Delete all?"));

        // System.out.println(console.getInt("Number please "));

        // System.out.println(console.getInt("Limited number please ", 0, 100));

        // System.out.println(console.getChoice("Choose ", new String[] { "One", "Two",
        // "Three" }));
    }
}