package CLI;

public class Colors {
    private String str;
    private static final String RESET = "[0m";

    public Colors(String inputStr) {
        str = inputStr;
    }

    public String toString() {
        return str;
    }

    public Colors black() {
        str = Constants.ESCAPE + "[30m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors red() {
        str = Constants.ESCAPE + "[31m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors green() {
        str = Constants.ESCAPE + "[32m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors yellow() {
        str = Constants.ESCAPE + "[33m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors blue() {
        str = Constants.ESCAPE + "[34m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors magenta() {
        str = Constants.ESCAPE + "[35m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors cyan() {
        str = Constants.ESCAPE + "[36m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors white() {
        str = Constants.ESCAPE + "[37m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgBlack() {
        str = Constants.ESCAPE + "[40m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgRed() {
        str = Constants.ESCAPE + "[41m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgGreen() {
        str = Constants.ESCAPE + "[42m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgYellow() {
        str = Constants.ESCAPE + "[43m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgBlue() {
        str = Constants.ESCAPE + "[44m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgMagenta() {
        str = Constants.ESCAPE + "[45m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgCyan() {
        str = Constants.ESCAPE + "[46m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bgWhite() {
        str = Constants.ESCAPE + "[47m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors bold() {
        str = Constants.ESCAPE + "[1m" + str + Constants.ESCAPE + RESET;
        return this;
    }

    public Colors underline() {
        str = Constants.ESCAPE + "[4m" + str + Constants.ESCAPE + RESET;
        return this;
    }
}
