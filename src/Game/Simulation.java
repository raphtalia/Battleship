package Game;

import CLI.Colors;
import Data.Vector2;

public class Simulation extends Game {
    // TODO: Figure out if all the max ship counts can be turned into a hashmap
    public Simulation(int boardWidth, int boardHeight, int maxCarriers, int maxBattleships, int maxCruisers,
            int maxSubmarines, int maxDestroyers, int maxPatrolBoats) {
        // TODO: Check if equivalent to Array.prototype.reduce
        super(boardWidth, boardHeight,
                maxCarriers + maxBattleships + maxCruisers + maxSubmarines + maxDestroyers + maxPatrolBoats);

        // TODO: If parameters can be a hashmap could loop over hashmap to place ships
        for (int i = 0; i < maxCarriers; i++) {
            this.placeShipRandom(new Ship(ShipType.CARRIER));
        }

        for (int i = 0; i < maxBattleships; i++) {
            this.placeShipRandom(new Ship(ShipType.BATTLESHIP));
        }

        for (int i = 0; i < maxCruisers; i++) {
            this.placeShipRandom(new Ship(ShipType.CRUISER));
        }

        for (int i = 0; i < maxSubmarines; i++) {
            this.placeShipRandom(new Ship(ShipType.SUBMARINE));
        }

        for (int i = 0; i < maxDestroyers; i++) {
            this.placeShipRandom(new Ship(ShipType.DESTROYER));
        }

        for (int i = 0; i < maxPatrolBoats; i++) {
            this.placeShipRandom(new Ship(ShipType.PATROL_BOAT));
        }
    }

    public Simulation placeShipRandom(Ship ship) {
        boolean shipPlaced = false;

        while (!shipPlaced) {
            final Vector2 bowLoc = new Vector2((int) (Math.random() * this.getBoardWidth()),
                    (int) (Math.random() * this.getBoardHeight()));
            final Direction dir = Math.random() < 0.5 ? Direction.HORIZONTAL : Direction.VERTICAL;

            if (this.isShipPlacementValid(ship, bowLoc, dir)) {
                this.placeShip(ship, bowLoc, dir);
                shipPlaced = true;
            }
        }

        return this;
    }

    public Simulation shootRandom() {
        boolean shot = false;

        while (!shot) {
            Vector2 target = new Vector2((int) (Math.random() * this.getBoardWidth()),
                    (int) (Math.random() * this.getBoardHeight()));

            if (!this.isShot(target)) {
                this.shoot(target);
                shot = true;
            }
        }

        return this;
    }

    public Simulation printMap() {
        // Meant for debugging

        for (int y = 0; y < this.getBoardHeight(); y++) {
            for (int x = 0; x < this.getBoardWidth(); x++) {
                final Vector2 loc = new Vector2(x, y);
                final Ship ship = this.getShipAt(loc);

                if (ship != null) {
                    if (isShot(loc)) {
                        System.out.print(new Colors(ship.getShipType().toString().substring(0, 1) + " ").bold().red());
                    } else {
                        System.out
                                .print(new Colors(ship.getShipType().toString().substring(0, 1) + " ").bold().yellow());
                    }
                } else if (this.isShot(loc)) {
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
