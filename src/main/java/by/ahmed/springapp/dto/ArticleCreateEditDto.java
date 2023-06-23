package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
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
