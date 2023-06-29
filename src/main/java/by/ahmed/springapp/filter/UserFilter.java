package by.ahmed.springapp.filter;

import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Gender;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
@Getter
public class UserFilter {
    String username;
    String first_name;
    String last_name;
    LocalDate birth_date;
    Gender gender;
    Authentication authentication;
}
