package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
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
    List<Article> articles;
}
