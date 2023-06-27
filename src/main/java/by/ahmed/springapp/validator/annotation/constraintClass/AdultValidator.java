package by.ahmed.springapp.validator.annotation.constraintClass;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.validator.annotation.Adult;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdultValidator implements ConstraintValidator<Adult, AuthorCreateEditDto> {
    @Override
    public boolean isValid(AuthorCreateEditDto value, ConstraintValidatorContext constraintValidatorContext) {
        int validValueYear = LocalDate.now().getYear() - value.getBirth_date().getYear();
        int validValueMonth = LocalDate.now().getMonthValue() - value.getBirth_date().getMonth();
        int validValueDay = LocalDate.now().getDayOfMonth() - value.getBirth_date().getDay();
        if (validValueYear < 18) {
            return false;
        }
        else if (validValueYear == 18 && validValueMonth < 0) {
            return false;
        }
        else if (validValueYear == 18 && validValueMonth == 0 && validValueDay < 0) {
            return false;
        }
        else return true;
    }
}
