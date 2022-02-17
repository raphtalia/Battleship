package Game;

import java.util.ArrayList;

import Data.Matrix;
import Data.Vector2;

public class Game {
    private Matrix<Boolean> hitMap;
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private int boardWidth;
    private int boardHeight;

    public Game(int inputBoardWidth, int inputBoardHeight) {
        hitMap = new Matrix<Boolean>(inputBoardWidth, inputBoardHeight, false);
        boardWidth = inputBoardWidth;
        boardHeight = inputBoardHeight;
    }

    public Game() {
        this(10, 10);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public boolean isShipPlacementValid(Ship ship, Vector2 bowLoc, Direction dir) {
        // TODO: Cut down on duplicate code

        Vector2 sternLoc;
        if (dir == Direction.HORIZONTAL) {
            sternLoc = new Vector2(bowLoc.getX() + ship.getLength() - 1, bowLoc.getY());
        } else {
            sternLoc = new Vector2(bowLoc.getX(), bowLoc.getY() + ship.getLength() - 1);
        }

        for (int x = (int) bowLoc.getX(); x <= (int) sternLoc.getX(); x++) {
            for (int y = (int) bowLoc.getY(); y <= (int) sternLoc.getY(); y++) {
                if (getShipAt(new Vector2(x, y)) != null) {
                    return false;
                }
            }
        }

        if (bowLoc.getX() < 0 || bowLoc.getX() >= boardWidth || bowLoc.getY() < 0 || bowLoc.getY() >= boardHeight) {
            return false;
        }

        if (sternLoc.getX() < 0 || sternLoc.getX() >= boardWidth || sternLoc.getY() < 0
                || sternLoc.getY() >= boardHeight) {
            return false;
        }

        if (bowLoc.getX() != sternLoc.getX() && bowLoc.getY() != sternLoc.getY()) {
            return false;
        }

        return true;
    }

    public Game placeShip(Ship ship, Vector2 bowLoc, Direction dir) {
        Vector2 sternLoc;
        if (dir == Direction.HORIZONTAL) {
            sternLoc = new Vector2(bowLoc.getX() + ship.getLength() - 1, bowLoc.getY());
        } else {
            sternLoc = new Vector2(bowLoc.getX(), bowLoc.getY() + ship.getLength() - 1);
        }

        for (int x = (int) bowLoc.getX(); x <= (int) sternLoc.getX(); x++) {
            for (int y = (int) bowLoc.getY(); y <= (int) sternLoc.getY(); y++) {
                if (getShipAt(new Vector2(x, y)) != null) {
                    throw new IllegalArgumentException("Ship already placed at " + new Vector2(x, y));
                }
            }
        }

        if (bowLoc.getX() < 0 || bowLoc.getX() >= boardWidth || bowLoc.getY() < 0 || bowLoc.getY() >= boardHeight) {
            throw new IllegalArgumentException("Bow location is out of bounds");
        }

        if (sternLoc.getX() < 0 || sternLoc.getX() >= boardWidth || sternLoc.getY() < 0
                || sternLoc.getY() >= boardHeight) {
            throw new IllegalArgumentException("Stern location is out of bounds");
        }

        if (bowLoc.getX() != sternLoc.getX() && bowLoc.getY() != sternLoc.getY()) {
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
             * if (dir == Direction.HORIZONTAL && loc.getX() == searchLoc.getX() &&
             * loc.getY() >=
             * searchLoc.getY()
             * && loc.getY() <= searchLoc.getY() + ship.getLength() - 1) {
             * return ship;
             * } else if (dir == Direction.VERTICAL && loc.getY() == searchLoc.getY() &&
             * loc.getX() >=
             * searchLoc.getX()
             * && loc.getX() <= searchLoc.getX() + ship.getLength() - 1) {
             * return ship;
             * }
             */

            if (dir == Direction.HORIZONTAL && loc.getY() == searchLoc.getY() && searchLoc.getX() >= loc.getX()
                    && searchLoc.getX() <= loc.getX() + ship.getLength() - 1) {
                return ship;
            } else if (dir == Direction.VERTICAL && loc.getX() == searchLoc.getX() && searchLoc.getY() >= loc.getY()
                    && searchLoc.getY() <= loc.getY() + ship.getLength() - 1) {
                return ship;
            }
        }

        return null;
    }

    public int getTotalShips() {
        return ships.size();
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
        if (target.getX() < 0 || target.getX() >= boardWidth || target.getY() < 0 || target.getY() >= boardHeight) {
            throw new IllegalArgumentException("Target location is out of bounds");
        }

        if (hitMap.get((int) target.getX(), (int) target.getY())) {
            throw new IllegalArgumentException("Target location has already been shot");
        }

        hitMap.set((int) target.getX(), (int) target.getY(), true);

        final Ship ship = getShipAt(target);
        if (ship != null) {
            ship.hit();
        }

        return this;
    }

    public boolean isShot(Vector2 loc) {
        return hitMap.get((int) loc.getX(), (int) loc.getY());
    }
}
