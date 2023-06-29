package by.ahmed.springapp.service;


import by.ahmed.springapp.dto.CommentCreateEditDto;
import by.ahmed.springapp.dto.CommentReadDto;
import by.ahmed.springapp.entity.Comment;
import by.ahmed.springapp.mapper.CommentListMapper;
import by.ahmed.springapp.mapper.CommentMapper;
import by.ahmed.springapp.mapper.CommentUpdateMapper;
import by.ahmed.springapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements by.ahmed.springapp.service.Service<CommentReadDto, CommentCreateEditDto> {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentListMapper commentListMapper;
    private final CommentUpdateMapper commentUpdateMapper;

    public List<CommentReadDto> findAll() {
        return commentListMapper.toDto(commentRepository.findAll());
    }

    public Page<Comment> getAll(Integer limit, Integer offset) {
        return commentRepository.findAll(PageRequest.of(offset, limit));
    }

    @Override
    public Optional<CommentReadDto> findById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDto);
    }

    @Override
    public CommentReadDto create(CommentCreateEditDto createEditDto) {
        return Optional.of(createEditDto)
                .map(commentMapper::toComment)
                .map(commentRepository::save)
                .map(commentMapper::toDto)
                .orElseThrow();
    }

    @Override
    public Optional<CommentReadDto> update(Long id, CommentCreateEditDto createEditDto) {
        return commentRepository.findById(id)
                .map(entity -> commentUpdateMapper.map(createEditDto, entity))
                .map(commentRepository::saveAndFlush)
                .map(commentMapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        return commentRepository.findById(id)
                .map(entity -> {
                    commentRepository.delete(entity);
                    commentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
