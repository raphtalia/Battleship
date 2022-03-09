package CLI;

public class Colors {
    private String str;

    public Colors(String inputStr) {
        str = inputStr;
    }

    public Colors(String format, String... args) {
        str = String.format(format, (Object[]) args);
    }

    public String toString() {
        return str;
    }

    public Colors black() {
        str = Constants.COLORS_BLACK + str + Constants.RESET;
        return this;
    }

    public Colors red() {
        str = Constants.COLORS_RED + str + Constants.RESET;
        return this;
    }

    public Colors green() {
        str = Constants.COLORS_GREEN + str + Constants.RESET;
        return this;
    }

    public Colors yellow() {
        str = Constants.COLORS_YELLOW + str + Constants.RESET;
        return this;
    }

    public Colors blue() {
        str = Constants.COLORS_BLUE + str + Constants.RESET;
        return this;
    }

    public Colors magenta() {
        str = Constants.COLORS_MAGENTA + str + Constants.RESET;
        return this;
    }

    public Colors cyan() {
        str = Constants.COLORS_CYAN + str + Constants.RESET;
        return this;
    }

    public Colors white() {
        str = Constants.COLORS_WHITE + str + Constants.RESET;
        return this;
    }

    public Colors bgBlack() {
        str = Constants.COLORS_BG_BLACK + str + Constants.RESET;
        return this;
    }

    public Colors bgRed() {
        str = Constants.COLORS_BG_RED + str + Constants.RESET;
        return this;
    }

    public Colors bgGreen() {
        str = Constants.COLORS_BG_GREEN + str + Constants.RESET;
        return this;
    }

    public Colors bgYellow() {
        str = Constants.COLORS_BG_YELLOW + str + Constants.RESET;
        return this;
    }

    public Colors bgBlue() {
        str = Constants.COLORS_BG_BLUE + str + Constants.RESET;
        return this;
    }

    public Colors bgMagenta() {
        str = Constants.COLORS_BG_MAGENTA + str + Constants.RESET;
        return this;
    }

    public Colors bgCyan() {
        str = Constants.COLORS_BG_CYAN + str + Constants.RESET;
        return this;
    }

    public Colors bgWhite() {
        str = Constants.COLORS_BG_WHITE + str + Constants.RESET;
        return this;
    }

    public Colors bold() {
        str = Constants.COLORS_BOLD + str + Constants.RESET;
        return this;
    }

    public Colors underline() {
        str = Constants.COLORS_UNDERLINE + str + Constants.RESET;
        return this;
    }
}
