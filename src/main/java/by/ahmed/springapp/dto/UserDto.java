package by.ahmed.springapp.dto;

import by.ahmed.springapp.entity.Comments;
import by.ahmed.springapp.entity.UserAuthentication;
import lombok.Value;

import java.util.List;

@Value
public class UserDto {
    Long id;
    String username;
    UserAuthentication authentication;
    List<Comments> comments;
}
