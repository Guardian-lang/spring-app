package by.ahmed.springapp.dto;

import lombok.Value;

import java.util.Date;

@Value
public class ArticleReadDto {
    Long id;
    String title;
    String announce;
    String fullText;
    Date date;
    Integer views;
    Long authorId;
}
