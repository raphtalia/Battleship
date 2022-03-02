package Game;

import CLI.Colors;
import Data.Vector2;

public class Player extends Game {
    public Player(int boardWidth, int boardHeight) {
        super(boardWidth, boardHeight);
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

    public Player printMap() {
        // Meant for debugging

        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                final Vector2 loc = new Vector2(x, y);
                final Ship ship = getShipAt(loc);

                if (ship != null) {
                    if (isShot(loc)) {
                        System.out.print(new Colors(ship.getShipType().toString().substring(0, 1) + " ").bold().red());
                    } else {
                        System.out
                                .print(new Colors(ship.getShipType().toString().substring(0, 1) + " ").bold().yellow());
                    }
                } else if (isShot(loc)) {
                    System.out.print(new Colors("* ").yellow());
                } else {
                    System.out.print(new Colors(". ").cyan());
                }
            }
            System.out.println();
        }

        return this;
    }
}
