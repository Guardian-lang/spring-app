package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Comment;
import by.ahmed.springapp.entity.Gender;
import by.ahmed.springapp.validator.annotation.Adult;
import by.ahmed.springapp.validator.annotation.UserInfo;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@UserInfo
@Adult
public class UserCreateEditDto {
    String username;
    String first_name;
    String last_name;
    LocalDate birth_date;
    Gender gender;
    String job_title;
    Authentication authentication;
    List<Comment> comments;
    List<Article> articles;
}
