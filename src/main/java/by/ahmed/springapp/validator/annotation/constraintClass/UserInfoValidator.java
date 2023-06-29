package by.ahmed.springapp.validator.annotation.constraintClass;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.validator.annotation.UserInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirst_name()) || hasText(value.getLast_name());
    }
}
