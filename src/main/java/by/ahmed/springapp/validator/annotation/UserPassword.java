package by.ahmed.springapp.validator.annotation;

import by.ahmed.springapp.validator.annotation.constraintClass.UserPasswordValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPassword {
}
