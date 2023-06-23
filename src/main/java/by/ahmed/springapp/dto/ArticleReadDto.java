package by.ahmed.springapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@Getter
public class ArticleReadDto {
    Long id;
    String title;
    String announce;
    String fullText;
    Date date;
    Integer views;
    Long authorId;
}
