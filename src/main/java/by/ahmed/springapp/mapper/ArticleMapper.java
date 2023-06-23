package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.entity.Article;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleReadDto toDto(Article article);
    Article toArticle(ArticleCreateEditDto dto);
}
