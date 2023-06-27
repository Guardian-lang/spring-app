package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.AdminDto;
import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.mapper.AdminMapper;
import by.ahmed.springapp.mapper.AdminUpdateMapper;
import by.ahmed.springapp.repository.AdminRepository;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;
    private AdminUpdateMapper adminUpdateMapper;

//    public Optional<AdminDto> login(String login, String password) {
//        Optional<AdminDto> adminDto = adminRepository.findAll()
//                .stream()
//                .filter(it -> it.getAuthentication().getEmail()
//                        .equals(login)
//                        && it.getAuthentication().getPassword().equals(password))
//                .map(adminMapper::toDto).findFirst();
//        var validationResult = loginAdminValidator.isValid(adminDto);
//        if (!validationResult.isValid()) {
//            throw new ValidationException(validationResult.getErrors());
//        }
//        return adminDto;
//    }

    public Optional<AdminDto> findById(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::toDto);
    }

    public Optional<AdminDto> findByFL(String firstname, String lastname) {
        return adminRepository.findByFL(firstname, lastname)
                .map(adminMapper::toDto);
    }

    public boolean create(AdminDto adminDto) {
        return Optional.of(adminDto)
                .map(adminMapper::toAdmin)
                .map(admin -> {
                    adminRepository.save(admin);
                    return true;
                })
                .orElse(false);
    }

    public boolean update(Long id, AdminDto adminDto) {
        return adminRepository.findById(id)
                .map(entity -> {
                    adminUpdateMapper.map(adminDto, entity);
                    adminRepository.saveAndFlush(entity);
                    return true;
                })
                .orElse(false);
    }

    public boolean delete(Long id) {
        return adminRepository.findById(id)
                .map(entity -> {
                    adminRepository.delete(entity);
                    adminRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
