package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.entity.Article;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ArticleUpdateMapper {
    default Article map(ArticleCreateEditDto dto, Article article) {
        article.setTitle(dto.getTitle());
        article.setAnnounce(dto.getAnnounce());
        article.setFull_text(dto.getFullText());
        article.setDate(dto.getDate());

        return article;
    }
}
