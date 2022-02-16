public class Main {
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

    public static void main(String[] args) {
        // int[][] board = new int[26][26];

        System.out.println(new Colors("Hello World!").red().bold().bgCyan());
    }
}