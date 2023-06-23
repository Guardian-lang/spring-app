package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class AuthorReadDto {
    Long id;
    String firstname;
    String lastname;
    String jobTitle;
    Date birthDate;
    String gender;
    List<Article> articles;
}
