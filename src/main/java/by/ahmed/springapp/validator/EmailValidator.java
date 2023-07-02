package by.ahmed.springapp.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {
    private List<String> emails;

    @Override
    public ValidationResult isValid(String object) {
        var validationResult = new ValidationResult();
        Pattern patternForEmail = Pattern.compile("REGEX_FOR_EMAIL");
        Matcher matcherForEmail = patternForEmail.matcher(object);
        if (!matcherForEmail.find()) {
            validationResult.add(Error.of(Error.getMessage("EMAIL"), "EMAIL_IS_WRONG"));
        }
        if (emails.contains(object)) {
            validationResult.add(Error.of(Error.getMessage("EMAIL"), "EMAIL_IS_USED"));
        }
        return validationResult;
    }
    public void putEmails(List<String> emails) {
        this.emails = emails;
    }
}
