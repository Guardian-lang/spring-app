package by.ahmed.springapp.validator.annotation.constraintClass;

import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.validator.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<Password, Authentication> {
    @Override
    public boolean isValid(Authentication authentication, ConstraintValidatorContext constraintValidatorContext) {
        String symbols = "!@#$%^&*?<>/*-+";
        String password = authentication.getPassword();
        int k = 0;
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < symbols.length(); j++) {
                if (password.charAt(i) == symbols.charAt(j)) {
                    k++;
                }
            }
        }
        return k > 0;
    }
}
