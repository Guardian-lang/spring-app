package by.ahmed.springapp.validator;

import java.util.List;

public class UsernameValidator implements Validator<String> {
    private List<String> usersNames;
    @Override
    public ValidationResult isValid(String object) {
        var validationResult = new ValidationResult();
        if (usersNames.contains(object)) {
            validationResult.add(Error.of(Error.getMessage("name"), "This login is already used."));
        }
        if (object == null) {
            validationResult.add(Error.of(Error.getMessage("name"), "Name is null."));
        }
        return validationResult;
    }

    public void putUsersNames(List<String> names) {
        this.usersNames = names;
    }
}
