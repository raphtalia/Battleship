import java.io.IOException;
import java.net.UnknownHostException;

import CLI.Console;
import Game.Direction;
import Game.Player;
import Game.Ship;
import Game.ShipType;
import Game.Multiplayer.Server;
import Game.Multiplayer.SocketWrapper;

public class ServerMain {
    public static void main(String[] args) throws UnknownHostException, IOException {
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

        final int boardWidth;
        final int boardHeight;
        if (console.getBoolean("Use default game settings?")) {
            boardWidth = 26;
            boardHeight = 26;
        } else {
            boardWidth = console.getInt("Board width: ", 5, 26);
            boardHeight = console.getInt("Board height: ", 5, 26);
        }

        final Server server = new Server(ip, port);

        System.out.println("Waiting for Player 1...");
        final SocketWrapper player1 = server.accept();
        final Player player1Game = new Player(boardWidth, boardHeight);
        {
            System.out.println("Player 1 connected");
            player1.write("SET_PLAYER_ID:Player1").read();
            player1Game.placeShipRandom(ShipType.BATTLESHIP);
            player1Game.placeShipRandom(ShipType.CARRIER);
            player1Game.placeShipRandom(ShipType.CRUISER);
            player1Game.placeShipRandom(ShipType.DESTROYER);
            player1Game.placeShipRandom(ShipType.PATROL_BOAT);
            player1Game.placeShipRandom(ShipType.SUBMARINE);

            String instruction = String.format("CREATE_BOARD:Player1:%d:%d,", boardWidth, boardHeight);

            for (Ship ship : player1Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player1:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }

            player1.write(instruction.substring(0, instruction.length() - 1)).read();
        }

        System.out.println("Waiting for Player 2...");
        final SocketWrapper player2 = server.accept();
        final Player player2Game = new Player(boardWidth, boardHeight);
        {
            System.out.println("Player 2 connected");
            player2.write("SET_PLAYER_ID:Player2").read();
            player2Game.placeShipRandom(ShipType.BATTLESHIP);
            player2Game.placeShipRandom(ShipType.CARRIER);
            player2Game.placeShipRandom(ShipType.CRUISER);
            player2Game.placeShipRandom(ShipType.DESTROYER);
            player2Game.placeShipRandom(ShipType.PATROL_BOAT);
            player2Game.placeShipRandom(ShipType.SUBMARINE);

            String instruction = String.format("CREATE_BOARD:Player2:%d:%d,", boardWidth, boardHeight);

            for (Ship ship : player1Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player2:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }

            player1.write(instruction.substring(0, instruction.length() - 1)).read();
        }

        while (true) {
            System.out.println(server.readAny());
        }
    }
}
