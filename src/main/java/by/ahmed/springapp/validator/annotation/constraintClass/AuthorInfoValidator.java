package by.ahmed.springapp.validator.annotation.constraintClass;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.validator.annotation.AuthorInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
public class AuthorInfoValidator implements ConstraintValidator<AuthorInfo, AuthorCreateEditDto> {

    @Override
    public boolean isValid(AuthorCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirst_name()) || hasText(value.getLast_name());
    }
}
