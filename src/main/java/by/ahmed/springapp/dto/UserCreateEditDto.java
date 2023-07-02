package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.*;
import by.ahmed.springapp.validator.annotation.Adult;
import by.ahmed.springapp.validator.annotation.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@UserInfo
@Adult
@Getter
@Setter
public class UserCreateEditDto {
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
