package by.ahmed.springapp.validator.annotation;

import by.ahmed.springapp.validator.annotation.constraintClass.AdultValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdultValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    String message() default "You are under 18! Are you sure you want to visit this site?";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
