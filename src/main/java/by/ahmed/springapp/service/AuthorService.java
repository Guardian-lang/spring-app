package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.entity.Author;
import by.ahmed.springapp.mapper.AuthorListMapper;
import by.ahmed.springapp.mapper.AuthorMapper;
import by.ahmed.springapp.mapper.AuthorUpdateMapper;
import by.ahmed.springapp.repository.AuthorRepository;
import by.ahmed.springapp.validator.LoginAuthorValidator;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService implements by.ahmed.springapp.service.Service<AuthorReadDto, AuthorCreateEditDto> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorListMapper authorListMapper;
    private final AuthorUpdateMapper authorUpdateMapper;
    private final ImageService imageService;
    private final LoginAuthorValidator loginAuthorValidator;

    public Optional<AuthorReadDto> login(String login, String password) {
        Optional<AuthorReadDto> userDto = authorRepository.findAll()
                .stream()
                .filter(it -> it.getAuthentication().getEmail()
                        .equals(login)
                        && it.getAuthentication().getPassword().equals(password))
                .map(authorMapper::toDto).findFirst();
        var validationResult = loginAuthorValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        return userDto;
    }

    public List<AuthorReadDto> findAll() {
        return authorListMapper.toDto(authorRepository.findAll());
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findAvatar(Long id) {
        return authorRepository.findById(id)
                .map(Author::getAvatar)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Override
    public Optional<AuthorReadDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto);
    }

    public List<AuthorReadDto> findAuthorsByFL(String firstname, String lastname) {
        return authorListMapper.toDto(authorRepository.findAuthorsByFL(firstname, lastname));
    }

    public AuthorReadDto findAuthorByArticle(String title) {
        return authorMapper.toDto(authorRepository.findAuthorByArticle(title));
    }

    @Transactional
    @Override
    public AuthorReadDto create(AuthorCreateEditDto authorCreateEditDto) {
        return Optional.of(authorCreateEditDto)
                .map(authorMapper::toAuthor)
                .map(authorRepository::save)
                .map(authorMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    @Override
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto createEditDto) {
        return authorRepository.findById(id)
                .map(entity -> authorUpdateMapper.map(createEditDto, entity))
                .map(authorRepository::saveAndFlush)
                .map(authorMapper::toDto);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    authorRepository.delete(entity);
                    authorRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
