package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.mapper.ArticleDtoConverter;
import by.ahmed.springapp.mapper.ArticleListMapper;
import by.ahmed.springapp.mapper.ArticleMapper;
import by.ahmed.springapp.mapper.ArticleUpdateMapper;
import by.ahmed.springapp.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ArticleListMapper articleListMapper;
    private final ArticleUpdateMapper articleUpdateMapper;
    private final ArticleDtoConverter articleDtoConverter;

    public List<ArticleReadDto> findAll() {
        return articleListMapper.toDtoList(articleRepository.findAll());
    }

    public Optional<ArticleReadDto> findById(Long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDto);
    }

    public ArticleReadDto create(ArticleCreateEditDto articleCreateEditDto) {
        return Optional.of(articleCreateEditDto)
                .map(articleDtoConverter::toReadDto)
                .map(articleDtoConverter::toCreateEditDto)
                .map(articleMapper::toArticle)
                .map(articleRepository::save)
                .map(articleMapper::toDto)
                .orElseThrow();
    }

    public Optional<ArticleReadDto> update(Long id, ArticleCreateEditDto createEditDto) {
        return articleRepository.findById(id)
                .map(entity -> articleUpdateMapper.map(createEditDto, entity))
                .map(articleRepository::saveAndFlush)
                .map(articleMapper::toDto);
    }

    public boolean delete(Long id) {
        return articleRepository.findById(id)
                .map(entity -> {
                    articleRepository.delete(entity);
                    articleRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
