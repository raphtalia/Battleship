package CLI;

import java.util.Scanner;

// Wrapper for ANSI codes, colors, and error safe Scanner
public class Console {
    private Scanner scanner = new Scanner(System.in);

    public Colors text(String str) {
        return new Colors(str);
    }

    public Colors text(String format, String... args) {
        return new Colors(format, args);
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

    // Fancy coloring of inputs
    public String nextLine() {
        System.out.print(Constants.COLORS_CYAN + Constants.COLORS_BOLD);
        return scanner.nextLine();
    }

    public int nextInt() {
        System.out.print(Constants.COLORS_CYAN + Constants.COLORS_BOLD);
        return scanner.nextInt();
    }

    // Doesn't wrap all methods, I'll add more when I need them
    public String getString(String message, String regex, String hint) {
        String input;

        cursorSave();

        while (true) {
            cursorRestore().cursorEraseAfterLine();
            System.out.print(message);
            input = nextLine();

            if (regex.length() == 0 || input.matches(regex)) {
                break;
            } else {
                System.out.println(text(hint).red().bold());
            }
        }

        cursorRestore().cursorEraseAfter();

        return input;
    }

    public String getString(String regex, String hint) {
        return getString("", regex, hint);
    }

    public String getString() {
        return getString("", "", "");
    }

    public String getString(String format, String... args) {
        return getString(String.format(format, (Object[]) args), "", "");
    }

    public String getString(String regex, String hint, String format, String... args) {
        return getString(String.format(format, (Object[]) args), regex, hint);
    }

    public int getInt(String message, int min, int max) {
        boolean limits = min != max;
        int input = 0;

        cursorSave();

        while (true) {
            try {
                cursorRestore().cursorEraseAfterLine();

                System.out.print(message);

                input = nextInt();

                if (!limits || (limits && input >= min && input <= max)) {
                    cursorRestore().cursorEraseAfterLine();
                    return input;
                } else {
                    System.out.println(
                            text("Input must be a number between %s and %s", String.valueOf(min), String.valueOf(max))
                                    .red().bold());
                }
            } catch (Exception e) {
                nextLine();
                if (limits) {
                    System.out.println(
                            text("Input must be a number between %s and %s", String.valueOf(min), String.valueOf(max))
                                    .red().bold());
                } else {
                    System.out.println(text("Input must be a number").red().bold());
                }
            }
        }
    }

    public int getInt() {
        return getInt("", 0, 0);
    }

    public int getInt(int min, int max) {
        return getInt("", min, max);
    }

    public int getInt(String format, String... args) {
        return getInt(String.format(format, (Object[]) args), 0, 0);
    }

    public int getInt(int min, int max, String format, String... args) {
        return getInt(String.format(format, (Object[]) args), min, max);
    }

    public boolean getBoolean(String message) {
        cursorSave();

        while (true) {
            if (message.length() > 0) {
                System.out.printf("%s [Y/n] ", message);
            } else {
                System.out.print("[Y/n] ");
            }

            final String input = nextLine();

            if (input.equalsIgnoreCase("yes".substring(0, input.length()))) {
                cursorRestore().cursorEraseAfter();
                return true;
            } else if (input.equalsIgnoreCase("no".substring(0, input.length()))) {
                cursorRestore().cursorEraseAfter();
                return false;
            } else {
                System.out.println(text("Input must be yes or no").red().bold());
                cursorRestore().cursorEraseAfterLine();
            }
        }
    }

    public boolean getBoolean() {
        return getBoolean("");
    }

    public boolean getBoolean(String format, String... args) {
        return getBoolean(String.format(format, (Object[]) args) + " ");
    }

    public int getChoice(String message, String[] choices) {
        if (message.length() > 0) {
            System.out.println(message);
        }

        for (int i = 0; i < choices.length; i++) {
            System.out.printf("[%d] %s\n", i + 1, choices[i]);
        }

        return getInt(message, 1, choices.length);
    }

    public int getChoice(String[] choices) {
        return getChoice("", choices);
    }

    public int getChoice(String[] choices, String format, String... args) {
        return getChoice(String.format(format, (Object[]) args), choices);
    }

    public Console close() {
        scanner.close();
        return this;
    }
}
