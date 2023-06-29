package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.CommentCreateEditDto;
import by.ahmed.springapp.entity.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CommentUpdateMapper {
    default Comment map(CommentCreateEditDto createEditDto, Comment comment) {
        comment.setUser(createEditDto.getUser());
        comment.setArticle(createEditDto.getArticle());
        comment.setDate(createEditDto.getDate());
        comment.setText(createEditDto.getText());

        return comment;
    }
}
