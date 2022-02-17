package CLI;

public class Colors {
    private String str;

    public Colors(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }

    // TODO: Set ANSI codes as constants, and support for 256 colors?
    public Colors black() {
        this.str = "\u001b[30m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors red() {
        this.str = "\u001b[31m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors green() {
        this.str = "\u001b[32m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors yellow() {
        this.str = "\u001b[33m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors blue() {
        this.str = "\u001b[34m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors magenta() {
        this.str = "\u001b[35m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors cyan() {
        this.str = "\u001b[36m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors white() {
        this.str = "\u001b[37m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgBlack() {
        this.str = "\u001b[40m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgRed() {
        this.str = "\u001b[41m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgGreen() {
        this.str = "\u001b[42m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgYellow() {
        this.str = "\u001b[43m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgBlue() {
        this.str = "\u001b[44m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgMagenta() {
        this.str = "\u001b[45m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgCyan() {
        this.str = "\u001b[46m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bgWhite() {
        this.str = "\u001b[47m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors bold() {
        this.str = "\u001b[1m" + this.str + "\u001b[0m";
        return this;
    }

    public Colors underline() {
        this.str = "\u001b[4m" + this.str + "\u001b[0m";
        return this;
    }
}
