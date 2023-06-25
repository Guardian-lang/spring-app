package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.dto.AuthorReadDto;
import by.ahmed.springapp.filter.AuthorFilter;
import by.ahmed.springapp.mapper.AuthorListMapper;
import by.ahmed.springapp.mapper.AuthorMapper;
import by.ahmed.springapp.mapper.AuthorPageMapper;
import by.ahmed.springapp.mapper.AuthorUpdateMapper;
import by.ahmed.springapp.repository.AuthorRepository;
import by.ahmed.springapp.repository.QPredicates;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.ahmed.springapp.entity.QAuthor.author;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService implements by.ahmed.springapp.service.Service<AuthorReadDto, AuthorCreateEditDto> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorListMapper authorListMapper;
    private final AuthorUpdateMapper authorUpdateMapper;
    private final AuthorPageMapper authorPageMapper;

    public Page<AuthorReadDto> findAll(AuthorFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), author.first_name::containsIgnoreCase)
                .add(filter.lastname(), author.last_name::containsIgnoreCase)
                .add(filter.birthDate(), author.birth_date::before)
                .build();
        return authorRepository.findAll(predicate, pageable)
                .map(authorPageMapper::toDto);
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

    @Override
    public AuthorReadDto create(AuthorCreateEditDto authorCreateEditDto) {
        return Optional.of(authorCreateEditDto)
                .map(authorMapper::toAuthor)
                .map(authorRepository::save)
                .map(authorMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto createEditDto) {
        return authorRepository.findById(id)
                .map(entity -> authorUpdateMapper.map(createEditDto, entity))
                .map(authorRepository::saveAndFlush)
                .map(authorMapper::toDto);
    }

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
