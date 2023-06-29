package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserDtoConverter {
    UserReadDto toReadDto(UserCreateEditDto createEditDto);
    UserCreateEditDto toCreateEditDto(UserReadDto readDto);
}
