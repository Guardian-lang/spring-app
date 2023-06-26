package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthorUpdateMapper {
    default Author map(AuthorCreateEditDto dto, Author author) {
        author.setFirst_name(dto.getFirst_name());
        author.setLast_name(dto.getLast_name());
        author.setBirth_date(dto.getBirth_date());
        author.setGender(dto.getGender());
        author.setJob_title(dto.getJob_title());
        author.setAuthentication(dto.getAuthentication());

        return author;
    }
}
