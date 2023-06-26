package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreateEditDto authorCreateEditDto);
    AuthorReadDto toDto(Author author);
}
