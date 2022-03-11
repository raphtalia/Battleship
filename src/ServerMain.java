import java.io.IOException;
import java.net.UnknownHostException;

import CLI.Console;
import Data.Vector2;
import Game.Direction;
import Game.Player;
import Game.Ship;
import Game.ShipType;
import Game.Networking.Server;
import Game.Networking.SocketWrapper;

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
        final int numBattleships;
        final int numCarriers;
        final int numCruisers;
        final int numDestroyers;
        final int numPatrolBoats;
        final int numSubmarines;
        if (console.getBoolean("Use default game settings?")) {
            boardWidth = 9;
            boardHeight = 9;
            numBattleships = 1;
            numCarriers = 1;
            numCruisers = 1;
            numDestroyers = 1;
            numPatrolBoats = 1;
            numSubmarines = 1;
        } else {
            boardWidth = console.getInt("Board width: ", 5, 26);
            boardHeight = console.getInt("Board height: ", 5, 26);
            numBattleships = console.getInt("Number of battleships: ", 0, 10);
            numCarriers = console.getInt("Number of carriers: ", 0, 10);
            numCruisers = console.getInt("Number of cruisers: ", 0, 10);
            numDestroyers = console.getInt("Number of destroyers: ", 0, 10);
            numPatrolBoats = console.getInt("Number of patrol boats: ", 0, 10);
            numSubmarines = console.getInt("Number of submarines: ", 0, 10);
        }

        final Server server = new Server(ip, port);

        // Create boards ahead of time
        final Player player1Game = new Player(boardWidth, boardHeight);
        player1Game.placeShipRandom(ShipType.BATTLESHIP, numBattleships);
        player1Game.placeShipRandom(ShipType.CARRIER, numCarriers);
        player1Game.placeShipRandom(ShipType.CRUISER, numCruisers);
        player1Game.placeShipRandom(ShipType.DESTROYER, numDestroyers);
        player1Game.placeShipRandom(ShipType.PATROL_BOAT, numPatrolBoats);
        player1Game.placeShipRandom(ShipType.SUBMARINE, numSubmarines);

        final Player player2Game = new Player(boardWidth, boardHeight);
        player2Game.placeShipRandom(ShipType.BATTLESHIP, numBattleships);
        player2Game.placeShipRandom(ShipType.CARRIER, numCarriers);
        player2Game.placeShipRandom(ShipType.CRUISER, numCruisers);
        player2Game.placeShipRandom(ShipType.DESTROYER, numDestroyers);
        player2Game.placeShipRandom(ShipType.PATROL_BOAT, numPatrolBoats);
        player2Game.placeShipRandom(ShipType.SUBMARINE, numSubmarines);

        // Setup Player 1
        System.out.println("Waiting for Player 1...");
        final SocketWrapper player1 = server.accept();
        {
            System.out.println("Player 1 connected");
            player1.write("SET_PLAYER_ID:Player1").read();

            String instruction = String.format("CREATE_BOARD:Player1:%d:%d,", boardWidth, boardHeight);

            for (Ship ship : player1Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player1:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }
            instruction += String.format("CREATE_BOARD:Player2:%d:%d,", boardWidth, boardHeight);
            for (Ship ship : player2Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player2:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }

            player1.write(instruction.substring(0, instruction.length() - 1)).read();
        }

        // Setup Player 2
        System.out.println("Waiting for Player 2...");
        final SocketWrapper player2 = server.accept();
        {
            System.out.println("Player 2 connected");
            player2.write("SET_PLAYER_ID:Player2").read();

            String instruction = String.format("CREATE_BOARD:Player2:%d:%d,", boardWidth, boardHeight);

            for (Ship ship : player2Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player2:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }
            instruction += String.format("CREATE_BOARD:Player1:%d:%d,", boardWidth, boardHeight);
            for (Ship ship : player1Game.getShips()) {
                instruction += String.format("ADD_SHIP:Player1:%s:%s:%s:%s,", ship.getShipType(),
                        (int) ship.getLocation().getX(), (int) ship.getLocation().getY(),
                        ship.getDirection() == Direction.HORIZONTAL ? "H" : "V");
            }

            player2.write(instruction.substring(0, instruction.length() - 1)).read();
        }

        player1.write("GAME_START").read();
        player2.write("GAME_START").read();

        int turn = (int) Math.round(Math.random());
        while (true) {
            if (turn % 2 == 0) {
                final String[] response = player1.write("TAKE_TURN:Player2").read().split(":");
                final int row = Integer.parseInt(response[0]);
                final int col = Integer.parseInt(response[1]);
                final Vector2 target = new Vector2(row, col);

                player2.write(String.format("ADD_HIT:Player2:%d:%d", row, col)).read();

                if (player2Game.shoot(target).getRemainingShips() == 0) {
                    player1.write("GAME_END:Player1");
                    player2.write("GAME_END:Player1");
                    System.out.println("Player 1 wins!");
                    break;
                }
            } else {
                final String[] response = player2.write("TAKE_TURN:Player1").read().split(":");
                final int row = Integer.parseInt(response[0]);
                final int col = Integer.parseInt(response[1]);
                final Vector2 target = new Vector2(row, col);

                player1.write(String.format("ADD_HIT:Player1:%d:%d", row, col)).read();

                if (player1Game.shoot(target).getRemainingShips() == 0) {
                    player1.write("GAME_END:Player2");
                    player2.write("GAME_END:Player2");
                    System.out.println("Player 2 wins!");
                    break;
                }
            }

            turn++;
        }
    }
}
