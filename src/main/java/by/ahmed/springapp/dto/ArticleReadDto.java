package by.ahmed.springapp.dto;

import lombok.*;

import java.time.LocalDate;
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
    LocalDate date;
    Integer views;
}
