package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.CommentCreateEditDto;
import by.ahmed.springapp.dto.CommentReadDto;
import by.ahmed.springapp.entity.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {
    List<CommentReadDto> toDto(List<Comment> comments);
    List<Comment> toComments(List<CommentCreateEditDto> dtos);
}
