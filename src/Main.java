import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import CLI.Colors;
import Game.ShipType;
import Game.Multiplayer.Client;
import Game.Multiplayer.Server;
import Game.Player;

public class Main {
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

        int role = (new Scanner(System.in)).nextInt();
        switch (role) {
            case 1:
                final Server server = new Server();
                server.connect("localhost", 3000);
                System.out.println(server.readAny());
                server.sendAll("hello from server!");
                break;
            case 2:
                final Client client = new Client("localhost", 3000);
                client.send("hello from client!");
                System.out.println(client.read());
                break;
        }
    }
}