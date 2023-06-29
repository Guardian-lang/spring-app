package by.ahmed.springapp.validator;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserValidator implements Validator<Optional<UserReadDto>> {
    @Override
    public ValidationResult isValid(Optional<UserReadDto> object) {
        var validationResult = new ValidationResult();
        if (object.isEmpty()) {
            validationResult.add(Error.of(Error.getMessage("login"), "You entered wrong login or password"));
        }
        return validationResult;
    }
}
