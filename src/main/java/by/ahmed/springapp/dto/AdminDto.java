package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Gender;
import by.ahmed.springapp.entity.Rules;
import lombok.Value;

import java.util.Date;

@Value
public class AdminDto {
    Long id;
    String first_name;
    String last_name;
    String job_title;
    Date birth_date;
    Gender gender;
    Rules rules;
}
