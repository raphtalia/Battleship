package CLI;

public class Colors {
    private String str;

    public Colors(String inputStr) {
        str = inputStr;
    }

    public String toString() {
        return str;
    }

    // TODO: Set ANSI codes as constants, and support for 256 colors?
    public Colors black() {
        str = "\u001b[30m" + str + "\u001b[0m";
        return this;
    }

    public Colors red() {
        str = "\u001b[31m" + str + "\u001b[0m";
        return this;
    }

    public Colors green() {
        str = "\u001b[32m" + str + "\u001b[0m";
        return this;
    }

    public Colors yellow() {
        str = "\u001b[33m" + str + "\u001b[0m";
        return this;
    }

    public Colors blue() {
        str = "\u001b[34m" + str + "\u001b[0m";
        return this;
    }

    public Colors magenta() {
        str = "\u001b[35m" + str + "\u001b[0m";
        return this;
    }

    public Colors cyan() {
        str = "\u001b[36m" + str + "\u001b[0m";
        return this;
    }

    public Colors white() {
        str = "\u001b[37m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgBlack() {
        str = "\u001b[40m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgRed() {
        str = "\u001b[41m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgGreen() {
        str = "\u001b[42m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgYellow() {
        str = "\u001b[43m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgBlue() {
        str = "\u001b[44m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgMagenta() {
        str = "\u001b[45m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgCyan() {
        str = "\u001b[46m" + str + "\u001b[0m";
        return this;
    }

    public Colors bgWhite() {
        str = "\u001b[47m" + str + "\u001b[0m";
        return this;
    }

    public Colors bold() {
        str = "\u001b[1m" + str + "\u001b[0m";
        return this;
    }

    public Colors underline() {
        str = "\u001b[4m" + str + "\u001b[0m";
        return this;
    }
}
