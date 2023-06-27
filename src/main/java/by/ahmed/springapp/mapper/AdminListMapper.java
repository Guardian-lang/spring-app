package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AdminDto;
import by.ahmed.springapp.entity.Admin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = AdminMapper.class)
public interface AdminListMapper {
    List<AdminDto> toDto(List<Admin> admins);
}
