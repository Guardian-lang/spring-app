package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AdminDto;
import by.ahmed.springapp.entity.Admin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDto toDto(Admin admin);
    Admin toAdmin(AdminDto adminDto);
}
