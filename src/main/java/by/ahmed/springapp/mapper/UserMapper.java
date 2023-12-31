package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadDto toDto(User user);
    User toUser(UserCreateEditDto userCreateEditDto);
}
