package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Authentication;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorReadDto {
    Long id;
    String firstname;
    String lastname;
    String jobTitle;
    Date birthDate;
    String gender;
    Authentication authentication;
    List<Article> articles;
}
