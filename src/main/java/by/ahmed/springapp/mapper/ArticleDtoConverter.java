package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface ArticleDtoConverter {
    List<ArticleCreateEditDto> toCreateEditDtoList(List<ArticleReadDto> readDtos);
    ArticleCreateEditDto toCreateEditDto(ArticleReadDto readDto);
    List<ArticleReadDto> toReadDtoList(List<ArticleCreateEditDto> createEditDtos);
    ArticleReadDto toReadDto(ArticleCreateEditDto createEditDto);
}
