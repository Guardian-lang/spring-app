package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import by.ahmed.springapp.entity.User;
import by.ahmed.springapp.mapper.UserMapper;
import by.ahmed.springapp.mapper.UserUpdateMapper;
import by.ahmed.springapp.repository.UserRepository;
import by.ahmed.springapp.validator.LoginUserValidator;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements by.ahmed.springapp.service.Service<UserReadDto, UserCreateEditDto>, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;
    private final LoginUserValidator loginUserValidator;
    private final ImageService imageService;

    public Optional<UserReadDto> login(String login, String password) {
        Optional<UserReadDto> userDto = userRepository.findAll()
                .stream()
                .filter(it -> it.getAuthentication().getEmail()
                        .equals(login)
                        && it.getAuthentication().getPassword().equals(password))
                .map(userMapper::toDto)
                .findFirst();
        var validationResult = loginUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        return userDto;
    }

    @Override
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    public Optional<UserReadDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }

    @Override
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(dto -> {
//                    uploadImage(dto.getAvatar());
                    return userMapper.toUser(dto);
                })
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()) {
            imageService.upload(image);
        }
    }

//    public Optional<byte[]> findAvatar(Long id) {
//        return userRepository.findById(id)
//                .map(User::getAvatar)
//                .filter(StringUtils::hasText)
//                .flatMap(imageService::get);
//    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
//                    uploadImage(userDto.getAvatar());
                    return userUpdateMapper.map(userDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(User::getAuthentication)
                .map(authentication -> new org.springframework.security.core.userdetails.User(
                        authentication.getEmail(),
                        authentication.getPassword(),
                        Collections.singleton(authentication.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrive user: " + username));
    }
}
