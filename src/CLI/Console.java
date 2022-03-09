package CLI;

import java.util.Scanner;

// Wrapper for ANSI codes, colors, and error safe Scanner
public class Console {
    private Scanner scanner = new Scanner(System.in);

    public Colors text(String str) {
        return new Colors(str);
    }

    public Console cursorHome() {
        System.out.print(Constants.ESCAPE + "[H");

        return this;
    }

    public Console cursorSave() {
        // Save twice for support with DEC and SCO terminals
        System.out.print(Constants.ESCAPE + " 7");
        System.out.print(Constants.ESCAPE + "[s");

        return this;
    }

    public Console cursorRestore() {
        // Restore twice for support with DEC and SCO terminals
        System.out.print(Constants.ESCAPE + " 8");
        System.out.print(Constants.ESCAPE + "[u");

        return this;
    }

    public Console cursorEraseBefore() {
        System.out.print(Constants.ESCAPE + "[1J");

        return this;
    }

    public Console cursorEraseAfter() {
        System.out.print(Constants.ESCAPE + "[0J");

        return this;
    }

    public Console cursorEraseBeforeLine() {
        System.out.print(Constants.ESCAPE + "[1K");

        return this;
    }

    public Console cursorEraseAfterLine() {
        System.out.print(Constants.ESCAPE + "[0K");

        return this;
    }

    public Console screenSave() {
        System.out.print(Constants.ESCAPE + "[?47h");

        return this;
    }

    public Console screenRestore() {
        System.out.print(Constants.ESCAPE + "[?47l");

        return this;
    }

    public Console erase() {
        System.out.print(Constants.ESCAPE + "[2J");

        return this;
    }

    // Doesn't wrap all methods, I'll add more when I need them
    public String nextLine() {
        return scanner.nextLine();
    }

    public String nextLine(String message) {
        System.out.print(message);
        return nextLine();
    }

    public String nextLine(String format, String... args) {
        System.out.printf(format, (Object[]) args);
        return nextLine();
    }

    // TODO: Make this only clear part of the input line
    public int nextInt() {
        boolean success = false;
        int input = 0;

        cursorSave();

        do {
            try {
                cursorRestore().cursorEraseAfterLine();

                input = scanner.nextInt();
                success = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(text("Invalid input").red().bold());
            }
        } while (!success);

        cursorEraseAfter();

        return input;
    }

    public int nextInt(String message) {
        System.out.print(message);
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
