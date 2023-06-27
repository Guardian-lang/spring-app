package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
