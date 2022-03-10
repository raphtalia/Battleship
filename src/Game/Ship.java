package Game;

import java.util.HashMap;

import Data.Vector2;

public class Ship {
    private final HashMap<ShipType, Integer> SHIP_SIZES = new HashMap<ShipType, Integer>() {
        {
            put(ShipType.CARRIER, 5);
            put(ShipType.BATTLESHIP, 4);
            put(ShipType.CRUISER, 3);
            put(ShipType.SUBMARINE, 3);
            put(ShipType.DESTROYER, 2);
            put(ShipType.PATROL_BOAT, 2);
        }
    };

    private Vector2 location;
    private Direction direction;
    private ShipType shipType;
    private int health;
    private int maxHealth;

    public Ship(ShipType inputShipType) {
        shipType = inputShipType;
        health = SHIP_SIZES.get(inputShipType);
        maxHealth = health;
    }

    public Ship setLocationAndDirection(Vector2 loc, Direction dir) {
        if (location != null || direction != null) {
            // Error is slightly misleading but if a location or direction is defined so
            // should the other
            throw new IllegalStateException("Ship already has a location and direction");
        }

        location = loc;
        direction = dir;

        return this;
    }

    public Vector2 getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLength() {
        return SHIP_SIZES.get(shipType);
    }

    public boolean isSunk() {
        return health <= 0;
    }

    public Ship hit() {
        if (isSunk()) {
            throw new IllegalStateException("Ship is already dead");
        }

        health--;
        return this;
    }
}
