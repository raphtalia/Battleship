package CLI.Inquirer;

import java.util.Scanner;
import java.util.function.Function;

import CLI.Colors;
import CLI.Console;

public class Inquiry {
    private final InquiryType inquiryType;
    private final String name;
    private final String message;
    private final String[] choices;
    private final Function<String, Boolean> validator;

    public Inquiry(InquiryType inquiryType, String name, String message, String[] choices,
            Function<String, Boolean> validator) {
        this.inquiryType = inquiryType;
        this.name = name;
        this.message = message;
        this.choices = choices;
        this.validator = validator;
    }

    public Inquiry inquire() {
        final Console console = new Console();

        console.cursorSave();

        switch (inquiryType) {
            case INPUT: {
                String choice;

                do {
                    choice = console.cursorRestore().cursorEraseAfter().nextLine("%s: ", message);
                } while (validator != null && validator.apply(choice));

                break;
            }
            case NUMBER: {
                int choice;

                do {
                    choice = console.cursorRestore().cursorEraseAfter().nextInt("%s: ", message);
                } while (validator != null && validator.apply(String.valueOf(choice)));

                break;
            }
            case CONFIRM: {
                String choice;

                do {
                    choice = console.cursorRestore().cursorEraseAfter().nextLine("%s: ", message);
                } while (validator != null && validator.apply(choice));

                break;
            }
            case LIST: {
                int choice;

                do {
                    console.cursorRestore().cursorEraseAfter();

                    for (int i = 0; i < choices.length; i++) {
                        System.out.printf("[%d] %s\n", i + 1, choices[i]);
                    }

                    choice = console.nextInt(message);
                } while (validator != null && validator.apply(String.valueOf(choice)));

                break;
            }
        }

        console.close();

        return this;
    }
}
