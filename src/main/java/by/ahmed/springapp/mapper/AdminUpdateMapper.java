package by.ahmed.springapp.mapper;

import by.ahmed.springapp.dto.AdminDto;
import by.ahmed.springapp.entity.Admin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AdminUpdateMapper {

    default Admin map(AdminDto adminDto, Admin admin) {
        admin.setFirst_name(adminDto.getFirst_name());
        admin.setLast_name(adminDto.getLast_name());
        admin.setBirth_date(adminDto.getBirth_date());
        admin.setGender(adminDto.getGender());
        admin.setJob_title(adminDto.getJob_title());
        admin.setRules(adminDto.getRules());

        return admin;
    }
}
