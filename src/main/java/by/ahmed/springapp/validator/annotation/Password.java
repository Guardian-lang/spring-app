package by.ahmed.springapp.validator.annotation;

import by.ahmed.springapp.validator.annotation.constraintClass.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "the password must contain at least 8 characters, of which at least one character is one of !@#$%^&*()?/*-+";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
