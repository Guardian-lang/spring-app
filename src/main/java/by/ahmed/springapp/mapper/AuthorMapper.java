package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreateEditDto authorCreateEditDto);
}
