package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.CommentCreateEditDto;
import by.ahmed.springapp.dto.CommentReadDto;
import by.ahmed.springapp.entity.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentReadDto toDto(Comment comment);
    Comment toComment(CommentCreateEditDto dto);
}
