package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.User;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CommentCreateEditDto {
    User user;
    Article article;
    LocalDate date;
    String text;
}
