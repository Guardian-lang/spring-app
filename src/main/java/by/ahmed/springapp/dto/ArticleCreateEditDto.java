package by.ahmed.springapp.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class ArticleCreateEditDto {
    String title;
    String announce;
    String fullText;
    Date date;
}
