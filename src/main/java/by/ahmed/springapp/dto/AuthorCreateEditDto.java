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
    String first_name;
    String last_name;
    String job_title;
    String avatar;
    Date birth_date;
    Gender gender;
    Authentication authentication;
}
