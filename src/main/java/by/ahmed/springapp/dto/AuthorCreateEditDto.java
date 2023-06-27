package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Gender;
import by.ahmed.springapp.validator.annotation.Adult;
import by.ahmed.springapp.validator.annotation.AuthorInfo;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AuthorInfo
@Adult
public class AuthorCreateEditDto {
    String first_name;
    String last_name;
    String job_title;
    String avatar;
    Date birth_date;
    Gender gender;
    Authentication authentication;
    List<Article> articles;
}
