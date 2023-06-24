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
public class ArticleService implements by.ahmed.springapp.service.Service<ArticleReadDto, ArticleCreateEditDto> {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ArticleListMapper articleListMapper;
    private final ArticleUpdateMapper articleUpdateMapper;

    public List<ArticleReadDto> findAll() {
        return articleListMapper.toDtoList(articleRepository.findAll());
    }

    @Override
    public Optional<ArticleReadDto> findById(Long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDto);
    }

    @Override
    public ArticleReadDto create(ArticleCreateEditDto createEditDto) {
        return Optional.of(createEditDto)
                .map(articleMapper::toArticle)
                .map(articleRepository::save)
                .map(articleMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Optional<ArticleReadDto> update(Long id, ArticleCreateEditDto createEditDto) {
        return articleRepository.findById(id)
                .map(entity -> articleUpdateMapper.map(createEditDto, entity))
                .map(articleRepository::saveAndFlush)
                .map(articleMapper::toDto);
    }

    @Override
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
