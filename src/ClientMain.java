import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import CLI.Console;
import Data.Vector2;
import Game.Direction;
import Game.Player;
import Game.Ship;
import Game.ShipType;
import Game.Networking.Client;

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

        final String ip;
        final int port;

        if (console.getBoolean("Use default IP and port?")) {
            ip = "127.0.0.1";
            port = 3000;
        } else {
            ip = console.getString("IP address: ", "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$", "Invalid IP address (A.B.C.D)");
            port = console.getInt("Port: ", 0, 65535);
        }
        final Client client = new Client(ip, port);

        // client.write("hello from client!" + Math.random());
        // System.out.println(client.read());
        // System.out.println(client.read());

        // System.out.println(console.getBoolean("Delete all?"));

        // System.out.println(console.getInt("Number please "));

        // System.out.println(console.getInt("Limited number please ", 0, 100));

        // System.out.println(console.getChoice("Choose ", new String[] { "One", "Two",
        // "Three" }));

        String localPlayerId;
        final HashMap<String, Player> players = new HashMap<String, Player>();

        while (true) {
            final String[] instructions = client.read().split(",");

            for (String instruction : instructions) {
                final String[] arguments = instruction.split(":");

                switch (arguments[0]) {
                    case "SET_PLAYER_ID": {
                        localPlayerId = arguments[1];
                        break;
                    }
                    case "CREATE_BOARD": {
                        final String boardId = arguments[1];
                        final int width = Integer.parseInt(arguments[2]);
                        final int height = Integer.parseInt(arguments[3]);

                        players.put(boardId, new Player(width, height));

                        break;
                    }
                    case "ADD_SHIP": {
                        final String boardId = arguments[1];
                        final String shipType = arguments[2];
                        final int x = Integer.parseInt(arguments[3]);
                        final int y = Integer.parseInt(arguments[4]);
                        final Direction dir = arguments[5].equals("H") ? Direction.HORIZONTAL : Direction.VERTICAL;

                        players.get(boardId).placeShip(new Ship(ShipType.valueOf(shipType)), new Vector2(x, y), dir);

                        break;
                    }
                    case "ADD_HIT": {
                        final String boardId = arguments[1];
                        final int x = Integer.parseInt(arguments[2]);
                        final int y = Integer.parseInt(arguments[3]);

                        players.get(boardId).shoot(new Vector2(x, y));

                        break;
                    }
                    case "TAKE_TURN": {
                        break;
                    }
                    case "REPORT_RESULTS": {
                        break;
                    }
                }
            }

            // Throwaway message used by server to confirm message received
            client.write("ACK");
        }
    }
}
