package Game;

import CLI.Colors;
import Data.Vector2;

public class Player extends Game {
    public Player(int boardWidth, int boardHeight) {
        super(boardWidth, boardHeight);
    }

    private static char getChar(int i) {
        if (i < 0 || i > 25) {
            throw new IllegalArgumentException("Invalid index: " + i);
        }

        return (char) (i + 'A');
    }

    public Player placeShipRandom(Ship ship) {
        boolean shipPlaced = false;

        while (!shipPlaced) {
            final Vector2 bowLoc = new Vector2((int) (Math.random() * getBoardWidth()),
                    (int) (Math.random() * getBoardHeight()));
            final Direction dir = Math.random() < 0.5 ? Direction.HORIZONTAL : Direction.VERTICAL;

            if (isShipPlacementValid(ship, bowLoc, dir)) {
                placeShip(ship, bowLoc, dir);
                shipPlaced = true;
            }
        }

        return this;
    }

    public Player placeShipRandom(ShipType shipType) {
        placeShipRandom(new Ship(shipType));

        return this;
    }

    public Player placeShipRandom(ShipType shipType, int numShips) {
        for (int i = 0; i < numShips; i++) {
            placeShipRandom(shipType);
        }

        return this;
    }

    public Player shootRandom() {
        boolean shot = false;

        while (!shot) {
            Vector2 target = new Vector2((int) (Math.random() * getBoardWidth()),
                    (int) (Math.random() * getBoardHeight()));

            if (!isShot(target)) {
                shoot(target);
                shot = true;
            }
        }

        return this;
    }

    public Player printMap(boolean hideShips) {
        System.out.print("    ");
        for (int x = 0; x < getBoardWidth(); x++) {
            System.out.print(new Colors(getChar(x) + "  ").green());
        }
        System.out.println();

        for (int y = 0; y < getBoardHeight(); y++) {
            System.out.print(new Colors(String.format("%2d  ", y + 1)).green());

            for (int x = 0; x < getBoardWidth(); x++) {
                final Vector2 loc = new Vector2(x, y);
                final Ship ship = getShipAt(loc);

                if (ship != null) {
                    if (isShot(loc)) {
                        if (hideShips) {
                            System.out.print(new Colors("X  ").bold().red());
                        } else {
                            System.out.print(
                                    new Colors(ship.getShipType().toString().substring(0, 1) + " ").bold().red());
                        }
                    } else {
                        if (hideShips) {
                            System.out.print(new Colors(".  ").cyan());
                        } else {
                            System.out.print(
                                    new Colors(ship.getShipType().toString().substring(0, 1) + "  ").bold().yellow());
                        }
                    }
                } else if (isShot(loc)) {
                    System.out.print(new Colors("*  ").yellow());
                } else {
                    System.out.print(new Colors(".  ").cyan());
                }
            }

            System.out.println();
        }

        return this;
    }
}
