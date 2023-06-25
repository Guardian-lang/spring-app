package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface AuthorPageMapper {
    Page<AuthorReadDto> toDto(Page<Author> authors);
}
