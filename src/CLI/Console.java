package CLI;

import java.util.Scanner;

// Wrapper for ANSI codes, colors, and error safe Scanner
public class Console {
    private Scanner scanner = new Scanner(System.in);

    public Colors text(String str) {
        return new Colors(str);
    }

    public Console cursorSave() {
        // Save twice for support with DEC and SCO terminals
        System.out.println(Constants.ESCAPE + " 7");
        System.out.println(Constants.ESCAPE + "[s");

        return this;
    }

    public Console cursorRestore() {
        // Restore twice for support with DEC and SCO terminals
        System.out.println(Constants.ESCAPE + " 8");
        System.out.println(Constants.ESCAPE + "[u");

        return this;
    }

    public Console cursorEraseBefore() {
        System.out.println(Constants.ESCAPE + "[1J");

        return this;
    }

    public Console cursorEraseAfter() {
        System.out.println(Constants.ESCAPE + "[0J");

        return this;
    }

    public Console erase() {
        System.out.println(Constants.ESCAPE + "[2J");

        return this;
    }

    // Doesn't wrap all methods, I'll add more when I need them
    public String nextLine() {
        return scanner.nextLine();
    }

    public String nextLine(String message) {
        System.out.printf("%s: ", message);
        return scanner.nextLine();
    }

    public String nextLine(String format, String... args) {
        System.out.printf(format, (Object[]) args);
        return scanner.nextLine();
    }

    // TODO: Make this only clear part of the input line
    public int nextInt() {
        boolean success = false;
        int input = 0;

        do {
            try {
                input = scanner.nextInt();
                success = true;
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!success);

        return input;
    }

    public int nextInt(String message) {
        System.out.printf("%s: ", message);
        return nextInt();
    }

    public int nextInt(String format, String... args) {
        System.out.printf(format, (Object[]) args);
        return nextInt();
    }

    public Console close() {
        scanner.close();
        return this;
    }
}
