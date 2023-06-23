package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Gender;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class AuthorCreateEditDto {
    String firstname;
    String lastname;
    String jobTitle;
    Date birthDate;
    Gender gender;
}
