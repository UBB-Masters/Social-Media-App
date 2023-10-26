package Entities.Misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String address;

    public Email(String address) {
        if (isValidEmail(address))
            this.address = address;
        else
            throw new IllegalArgumentException("Invalid email address");
    }

    private boolean isValidEmail(String address) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String address) {
        if (isValidEmail(address)) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
