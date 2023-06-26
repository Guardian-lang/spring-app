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
    String first_name;
    String last_name;
    String job_title;
    String avatar;
    Date birth_date;
    String gender;
    Authentication authentication;
    List<Article> articles;
}
