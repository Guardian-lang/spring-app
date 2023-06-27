package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserDto> toDto(List<User> users);
    List<User> toUsers(List<UserDto> userDtos);
}
