package model.validation;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alex on 18/03/2017.
 */
public class UserValidator {
    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final User user;

    public UserValidator(User user) {
        this.user = user;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateCNP(user.getCNP());
        validatePhoneNr(user.getPhoneNr());
        return errors.isEmpty();
    }
    public String getFormattedErrors() {
        return String.join("\n", errors);
    }
    private void validateUsername(String username) {
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid email!");
        }
    }
    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password too short!");
        }
        if (!containsSpecialCharacter(password)) {
            errors.add("Password must contain at least one special character!");
        }
        if (!containsDigit(password)) {
            errors.add("Password must contain at least one number!");
        }
    }
    private void validateCNP(String cnp) {
        if (!cnp.equals("") && cnp.length()!=13)
            errors.add("CNP too short!");
        else{
            if(!cnp.equals("")){
                if(cnp.charAt(0) != '1' && cnp.charAt(0) != '2')
                    errors.add("Even if you're not binary, your CNP 1st digit is! You are valid <3");
                int day = Integer.parseInt(cnp.substring(1,3));
                int month = Integer.parseInt(cnp.substring(3,5));
                if(month>12)
                    errors.add("CNP invalid! - Month");
                if(day>31)
                    errors.add("CNP invalid! - Day");
                if((month == 4 || month==6 || month==9 || month==11) && day>30)
                    errors.add("CNP invalid! - Day & Month combination");
                if(month == 2 && day>28)
                    errors.add("CNP invalid! - Day & Month combination");
            }
        }

    }
    private void validatePhoneNr(String phoneNr) {
        if (!phoneNr.equals("") && phoneNr.length()!=10)
            errors.add("PhoneNr too short!");
        else if (!phoneNr.equals("") ){
            if(phoneNr.charAt(0) != '0')
                errors.add("PhoneNr incorrect!");
            if(!areDigit(phoneNr))
                errors.add("PhoneNr incorrect! Can only contain digits!");
        }
    }
    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean areDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

}
