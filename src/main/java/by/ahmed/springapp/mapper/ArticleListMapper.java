package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.entity.Article;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring", uses = ArticleMapper.class)
public interface ArticleListMapper {
    List<ArticleReadDto> toDtoList(List<Article> articles);
    List<Article> toArticles(List<ArticleCreateEditDto> dtos);
}
