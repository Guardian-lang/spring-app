package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.UserDto;
import by.ahmed.springapp.mapper.UserListMapper;
import by.ahmed.springapp.mapper.UserMapper;
import by.ahmed.springapp.mapper.UserUpdateMapper;
import by.ahmed.springapp.repository.UserRepository;
import by.ahmed.springapp.validator.LoginUserValidator;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;
    private final LoginUserValidator loginUserValidator;

    public Optional<UserDto> login(String login, String password) {
        Optional<UserDto> userDto = userRepository.findAll()
                .stream()
                .filter(it -> it.getAuthentication().getEmail()
                        .equals(login)
                        && it.getAuthentication().getPassword().equals(password))
                .map(userMapper::toDto).findFirst();
        var validationResult = loginUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        return userDto;
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }

    public boolean create(UserDto userDto) {
        return Optional.of(userDto)
                .map(userMapper::toUser)
                .map(user -> {
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    public boolean update(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    userUpdateMapper.map(userDto, entity);
                    userRepository.saveAndFlush(entity);
                    return true;
                })
                .orElse(false);
    }

    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
