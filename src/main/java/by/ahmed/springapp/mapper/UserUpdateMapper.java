package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
    default User map(UserCreateEditDto userCreateEditDto, User user) {
        user.setUsername(userCreateEditDto.getUsername());
        user.setAvatar(userCreateEditDto.getAvatar());
        user.setFirst_name(userCreateEditDto.getFirst_name());
        user.setLast_name(userCreateEditDto.getLast_name());
        user.setBirth_date(userCreateEditDto.getBirth_date());
        user.setGender(user.getGender());
        user.setAuthentication(userCreateEditDto.getAuthentication());
        user.setArticles(userCreateEditDto.getArticles());
        user.setComments(userCreateEditDto.getComments());

        return user;
    }
}
