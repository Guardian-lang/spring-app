package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class UserReadDto {
    Long id;
    String username;
    Image avatar;
    String first_name;
    String last_name;
    LocalDate birth_date;
    Gender gender;
    String job_title;
    Authentication authentication;
    List<Comment> comments;
    List<Article> articles;
}
