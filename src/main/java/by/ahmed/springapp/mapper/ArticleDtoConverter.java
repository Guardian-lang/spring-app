package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleDtoConverter {
    List<ArticleCreateEditDto> toCreateEditDtoList(List<ArticleReadDto> readDtos);
    ArticleCreateEditDto toCreateEditDto(ArticleReadDto readDto);
    List<ArticleReadDto> toReadDtoList(List<ArticleCreateEditDto> createEditDtos);
    ArticleReadDto toReadDto(ArticleCreateEditDto createEditDto);
}
