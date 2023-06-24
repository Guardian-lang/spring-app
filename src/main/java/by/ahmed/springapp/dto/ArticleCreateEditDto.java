package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Author;
import lombok.*;

import java.util.Date;

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
    Date date;
    Author author;
}
