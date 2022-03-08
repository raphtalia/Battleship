package CLI.Inquirer;

import java.util.Vector;
import java.util.function.Function;

public class Inquirer {
    private Vector<Inquiry> inquiries = new Vector<Inquiry>();

    public Inquirer inquire(InquiryType inquiryType, String name, String message, String[] choices,
            Function<String, Boolean> validator) {
        inquiries.add(new Inquiry(inquiryType, name, message, choices, validator));
        return this;
    }

    public Inquirer inquire(InquiryType inquiryType, String name, String message, String[] choices) {
        return inquire(inquiryType, name, message, choices, null);
    }

    public Inquirer inquire(InquiryType inquiryType, String name, String message) {
        return inquire(inquiryType, name, message, null, null);
    }

    public Inquirer inquire(InquiryType inquiryType, String name) {
        return inquire(inquiryType, name, name, null, null);
    }

    public Inquirer prompt() {
        for (Inquiry inquiry : inquiries) {
            inquiry.inquire();
        }

        return this;
    }
}
