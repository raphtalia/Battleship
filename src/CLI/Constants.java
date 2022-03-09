package CLI;

public class Constants {
    public static final String ESCAPE = "\u001b";

    public static final String RESET = ESCAPE + "[0m";

    public static final String COLORS_BLACK = ESCAPE + "[30m";
    public static final String COLORS_RED = ESCAPE + "[31m";
    public static final String COLORS_GREEN = ESCAPE + "[32m";
    public static final String COLORS_YELLOW = ESCAPE + "[33m";
    public static final String COLORS_BLUE = ESCAPE + "[34m";
    public static final String COLORS_MAGENTA = ESCAPE + "[35m";
    public static final String COLORS_CYAN = ESCAPE + "[36m";
    public static final String COLORS_WHITE = ESCAPE + "[37m";

    public static final String COLORS_BG_BLACK = ESCAPE + "[40m";
    public static final String COLORS_BG_RED = ESCAPE + "[41m";
    public static final String COLORS_BG_GREEN = ESCAPE + "[42m";
    public static final String COLORS_BG_YELLOW = ESCAPE + "[43m";
    public static final String COLORS_BG_BLUE = ESCAPE + "[44m";
    public static final String COLORS_BG_MAGENTA = ESCAPE + "[45m";
    public static final String COLORS_BG_CYAN = ESCAPE + "[46m";
    public static final String COLORS_BG_WHITE = ESCAPE + "[47m";

    public static final String COLORS_BOLD = ESCAPE + "[1m";
    public static final String COLORS_UNDERLINE = ESCAPE + "[4m";
}
