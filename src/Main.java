import Colors.*;

public class Main {
    public static void main(String[] args) {
        Colors colors = new Colors(NumSupportedColors.COLORS_256);

        System.out.println(colors.red("Hello World!"));
    }
}