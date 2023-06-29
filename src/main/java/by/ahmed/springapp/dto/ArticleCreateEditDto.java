package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.User;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticleCreateEditDto {
    String title;
    String announce;
    String fullText;
    LocalDate date;
    User user;
}
