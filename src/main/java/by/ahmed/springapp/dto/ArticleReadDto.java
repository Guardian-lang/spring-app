package by.ahmed.springapp.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
