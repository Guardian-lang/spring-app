package by.ahmed.springapp.validator.annotation.constraintClass;

import by.ahmed.springapp.entity.UserAuthentication;
import by.ahmed.springapp.validator.annotation.UserPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidator implements ConstraintValidator<UserPassword, UserAuthentication> {
    @Override
    public boolean isValid(UserAuthentication authentication, ConstraintValidatorContext constraintValidatorContext) {
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
