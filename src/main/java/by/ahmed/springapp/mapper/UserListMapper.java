package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserReadDto> toDto(List<User> users);
    List<User> toUsers(List<UserCreateEditDto> userCreateEditDtos);
}
