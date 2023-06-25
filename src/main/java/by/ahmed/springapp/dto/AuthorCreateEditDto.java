package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Gender;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorCreateEditDto {
    String firstname;
    String lastname;
    String jobTitle;
    Date birthDate;
    Gender gender;
    Authentication authentication;
}
