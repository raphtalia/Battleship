package Game;

import java.util.Vector;

import Data.Matrix;
import Data.Vector2;

public class Game {
    private Matrix<Boolean> hitMap;
    private Vector<Ship> ships = new Vector<Ship>();
    private int boardWidth;
    private int boardHeight;

    public Game(int boardWidth, int boardHeight, int maxShips) {
        this.hitMap = new Matrix<Boolean>(boardWidth, boardHeight, false);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public Game(int boardWidth, int boardHeight) {
        this(boardWidth, boardHeight, 6);
    }

    public Game() {
        this(10, 10);
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public boolean isShipPlacementValid(Ship ship, Vector2 bowLoc, Direction dir) {
        // TODO: Cut down on duplicate code

        Vector2 sternLoc;
        if (dir == Direction.HORIZONTAL) {
            sternLoc = new Vector2(bowLoc.x + ship.getLength() - 1, bowLoc.y);
        } else {
            sternLoc = new Vector2(bowLoc.x, bowLoc.y + ship.getLength() - 1);
        }

        for (int x = (int) bowLoc.x; x <= (int) sternLoc.x; x++) {
            for (int y = (int) bowLoc.y; y <= (int) sternLoc.y; y++) {
                if (this.getShipAt(new Vector2(x, y)) != null) {
                    return false;
                }
            }
        }

        if (bowLoc.x < 0 || bowLoc.x >= boardWidth || bowLoc.y < 0 || bowLoc.y >= boardHeight) {
            return false;
        }

        if (sternLoc.x < 0 || sternLoc.x >= boardWidth || sternLoc.y < 0 || sternLoc.y >= boardHeight) {
            return false;
        }

        if (bowLoc.x != sternLoc.x && bowLoc.y != sternLoc.y) {
            return false;
        }

        return true;
    }

    public Game placeShip(Ship ship, Vector2 bowLoc, Direction dir) {
        Vector2 sternLoc;
        if (dir == Direction.HORIZONTAL) {
            sternLoc = new Vector2(bowLoc.x + ship.getLength() - 1, bowLoc.y);
        } else {
            sternLoc = new Vector2(bowLoc.x, bowLoc.y + ship.getLength() - 1);
        }

        for (int x = (int) bowLoc.x; x <= (int) sternLoc.x; x++) {
            for (int y = (int) bowLoc.y; y <= (int) sternLoc.y; y++) {
                if (this.getShipAt(new Vector2(x, y)) != null) {
                    throw new IllegalArgumentException("Ship already placed at " + new Vector2(x, y));
                }
            }
        }

        if (bowLoc.x < 0 || bowLoc.x >= boardWidth || bowLoc.y < 0 || bowLoc.y >= boardHeight) {
            throw new IllegalArgumentException("Bow location is out of bounds");
        }

        if (sternLoc.x < 0 || sternLoc.x >= boardWidth || sternLoc.y < 0 || sternLoc.y >= boardHeight) {
            throw new IllegalArgumentException("Stern location is out of bounds");
        }

        if (bowLoc.x != sternLoc.x && bowLoc.y != sternLoc.y) {
            throw new IllegalArgumentException("Ship must be placed horizontally or vertically");
        }

        ship.setLocationAndDirection(bowLoc, dir);
        ships.add(ship);

        return this;
    }

    public Ship getShipAt(Vector2 searchLoc) {
        for (Ship ship : ships) {
            final Direction dir = ship.getDirection();
            final Vector2 loc = ship.getLocation();

            /*
             * if (dir == Direction.HORIZONTAL && loc.x == searchLoc.x && loc.y >=
             * searchLoc.y
             * && loc.y <= searchLoc.y + ship.getLength() - 1) {
             * return ship;
             * } else if (dir == Direction.VERTICAL && loc.y == searchLoc.y && loc.x >=
             * searchLoc.x
             * && loc.x <= searchLoc.x + ship.getLength() - 1) {
             * return ship;
             * }
             */

            if (dir == Direction.HORIZONTAL && loc.y == searchLoc.y && searchLoc.x >= loc.x
                    && searchLoc.x <= loc.x + ship.getLength() - 1) {
                return ship;
            } else if (dir == Direction.VERTICAL && loc.x == searchLoc.x && searchLoc.y >= loc.y
                    && searchLoc.y <= loc.y + ship.getLength() - 1) {
                return ship;
            }
        }

        return null;
    }

    public int getTotalShips() {
        return this.ships.size();
    }

    public int getRemainingShips() {
        int remaining = 0;

        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                remaining++;
            }
        }

        return remaining;
    }

    public Game shoot(Vector2 target) {
        if (target.x < 0 || target.x >= boardWidth || target.y < 0 || target.y >= boardHeight) {
            throw new IllegalArgumentException("Target location is out of bounds");
        }

        if (hitMap.get((int) target.x, (int) target.y)) {
            throw new IllegalArgumentException("Target location has already been shot");
        }

        hitMap.set((int) target.x, (int) target.y, true);

        final Ship ship = getShipAt(target);
        if (ship != null) {
            ship.hit();
        }

        return this;
    }

    public boolean isShot(Vector2 loc) {
        return hitMap.get((int) loc.x, (int) loc.y);
    }
}
