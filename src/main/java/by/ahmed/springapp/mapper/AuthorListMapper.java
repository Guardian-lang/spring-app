package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface AuthorListMapper {

    List<Author> toAuthor(List<AuthorCreateEditDto> dtoList);
    List<AuthorReadDto> toDto(List<Author> authors);
}
