import java.io.IOException;
import java.net.UnknownHostException;

import CLI.Inquirer.Inquirer;
import CLI.Inquirer.InquiryType;
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

        /*
         * final Client client = new Client("192.168.1.94", 3000);
         * client.send("hello from client!");
         * System.out.println(client.read());
         */

        final Inquirer inquirer = new Inquirer();

        inquirer.inquire(InquiryType.LIST, "Choice", "Choose the following",
                new String[] { "Choice 1", "Choice 2", "Choice 3" }).prompt();
    }
}