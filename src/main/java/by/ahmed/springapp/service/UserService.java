package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.dto.UserReadDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Image;
import by.ahmed.springapp.entity.User;
import by.ahmed.springapp.mapper.ArticleMapper;
import by.ahmed.springapp.mapper.UserDtoConverter;
import by.ahmed.springapp.mapper.UserMapper;
import by.ahmed.springapp.mapper.UserUpdateMapper;
import by.ahmed.springapp.repository.UserRepository;
import by.ahmed.springapp.validator.LoginUserValidator;
import by.ahmed.springapp.validator.PasswordValidator;
import by.ahmed.springapp.validator.UsernameValidator;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements by.ahmed.springapp.service.Service<UserReadDto, UserCreateEditDto>, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;
    private final ArticleMapper articleMapper;
    private final LoginUserValidator loginUserValidator;
    private final ImageService imageService;
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;
    private final UserDtoConverter userDtoConverter;

    public Page<User> getAll(Integer offset, Integer limit) {
        return userRepository.findAll(PageRequest.of(offset, limit));
    }

    public Optional<UserReadDto> addArticle(Long id, ArticleCreateEditDto articleDto) {
        var author = userRepository.findById(id)
                .map(userMapper::toDto)
                .map(userDtoConverter::toCreateEditDto)
                .orElseThrow();
        var article = articleMapper.toArticle(articleDto);
        List<Article> articles = author.getArticles();
        articles.add(article);
        author.setArticles(articles);

        return update(id, author);
    }

    @Transactional
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

    @Transactional
    @Override
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(dto -> {
                    uploadImage(dto.getAvatar().getContent());
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

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getAvatar)
                .map(Image::getPath)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public UserReadDto setNewAvatar(Long id, Image image) {
        var user = userRepository.findById(id).orElseThrow();
        var readUser = userMapper.toDto(user);
        var updateUser = userDtoConverter.toCreateEditDto(readUser);
        return Optional.of(updateUser)
                .map(dto -> {
                    uploadImage(image.getContent());
                    return userUpdateMapper.map(dto, user);
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    private List<String> getAllNames() {
        return userRepository.findAllByUsernameNotNull();
    }

    private List<String> getAllEmails() {
        return userRepository.findAllByEmailNotNull();
    }

    @Transactional
    public void setNewUsername(UserCreateEditDto userDto, String newUsername, String password) {
        validateOldPassword(userDto, password);
        usernameValidator.putUsersNames(getAllNames());
        var validationResultName = usernameValidator.isValid(newUsername);
        if (!validationResultName.isValid()) {
            throw new ValidationException(validationResultName.getErrors());
        }
        userDto.setUsername(newUsername);
        userRepository.saveAndFlush(userMapper.toUser(userDto));
    }

    @Transactional
    public void setNewPassword(UserCreateEditDto userDto, String oldPassword, String newPassword) {
        validateOldPassword(userDto, oldPassword);
        var validationResultNewPassword = passwordValidator.isValid(newPassword);
        if (!validationResultNewPassword.isValid()) {
            throw new ValidationException(validationResultNewPassword.getErrors());
        }
        Authentication authentication = userDto.getAuthentication();
        authentication.setPassword(newPassword);
        userDto.setAuthentication(authentication);
        userRepository.save(userMapper.toUser(userDto));
    }

    private void validateOldPassword(UserCreateEditDto userDto, String password) {
        var validationResultPassword = passwordValidator.isValid(userDto, password);
        if (!validationResultPassword.isValid()) {
            throw new ValidationException(validationResultPassword.getErrors());
        }
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    uploadImage(userDto.getAvatar().getContent());
                    return userUpdateMapper.map(userDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto);
    }

    @Transactional
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
