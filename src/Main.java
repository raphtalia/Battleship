import CLI.Colors;
import Game.ShipType;
import Game.Simulation;

public class Main {
    // private static int indexOfAlphabet(char c) {
    // c = Character.toUpperCase(c);

    // if (c < 'A' || c > 'Z') {
    // throw new IllegalArgumentException("Invalid character: " + c);
    // }

    // return c - 'A';
    // }

    // private static char getChar(int i) {
    // if (i < 0 || i > 25) {
    // throw new IllegalArgumentException("Invalid index: " + i);
    // }

    // return (char) (i + 'A');
    // }

    public static void main(String[] args) {
        System.out.println(new Colors("Battleship").bold().red());

        final Simulation sim = new Simulation(26, 26);

        sim.placeShipRandom(ShipType.CARRIER, 2);
        sim.placeShipRandom(ShipType.BATTLESHIP, 2);
        sim.placeShipRandom(ShipType.CRUISER, 2);
        sim.placeShipRandom(ShipType.SUBMARINE, 2);
        sim.placeShipRandom(ShipType.DESTROYER, 2);
        sim.placeShipRandom(ShipType.PATROL_BOAT, 2);

        for (int i = 0; i < 400; i++) {
            sim.shootRandom();
        }

        sim.printMap();

        System.out.printf("%s out of %s ships remain", sim.getRemainingShips(), sim.getTotalShips());
    }
}