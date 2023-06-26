package by.ahmed.springapp.validator;

import by.ahmed.springapp.dto.AuthorReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginAuthorValidator implements Validator<Optional<AuthorReadDto>> {
    @Override
    public ValidationResult isValid(Optional<AuthorReadDto> object) {
        var validationResult = new ValidationResult();
        if (object.isEmpty()) {
            validationResult.add(Error.of(Error.getMessage("login"), "You entered wrong login or password"));
        }
        return validationResult;
    }
}
