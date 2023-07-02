package by.ahmed.springapp.validator;

import by.ahmed.springapp.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator<String>{

    @Override
    public ValidationResult isValid(String object) {
        var validationResult = new ValidationResult();
        Pattern patternForPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}$");
        Matcher matcherForPassword = patternForPassword.matcher(object);
        if (!matcherForPassword.find()) {
            validationResult.add(Error.of(Error.getMessage("password"), "Password is simple. " +
                    "The password must be at least 8 characters long, contain at least 1 uppercase letter, " +
                    "1 lowercase letter and EITHER a special character OR a number. " ));
        }
        return validationResult;
    }

    public ValidationResult isValid(UserCreateEditDto userDto, String password) {
        var validationResult = new ValidationResult();
        if (!userDto.getAuthentication().getPassword().equals(password)) {
            validationResult.add(Error.of(Error.getMessage("password"), "Password is wrong."));
        }
        return validationResult;
    }
}
