package by.ahmed.springapp.validator;

import by.ahmed.springapp.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserValidator implements Validator<Optional<UserDto>> {
    @Override
    public ValidationResult isValid(Optional<UserDto> object) {
        var validationResult = new ValidationResult();
        if (object.isEmpty()) {
            validationResult.add(Error.of(Error.getMessage("login"), "You entered wrong login or password"));
        }
        return validationResult;
    }
}
