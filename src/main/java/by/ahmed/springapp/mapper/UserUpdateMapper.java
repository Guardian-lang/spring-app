package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
    default User map(UserDto userDto, User user) {
        user.setUsername(userDto.getUsername());
        user.setAuthentication(userDto.getAuthentication());
        user.setComments(userDto.getComments());

        return user;
    }
}
