package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthorUpdateMapper {

    default Author map(AuthorCreateEditDto dto, Author author) {
        author.setFirst_name(dto.getFirstname());
        author.setLast_name(dto.getLastname());
        author.setBirth_date(dto.getBirthDate());
        author.setGender(dto.getGender());
        author.setJob_title(dto.getJobTitle());

        return author;
    }
}
