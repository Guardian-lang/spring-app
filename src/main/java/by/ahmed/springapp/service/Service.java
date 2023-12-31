package by.ahmed.springapp.service;

import by.ahmed.springapp.dto.UserReadDto;

import java.util.Optional;

public interface Service<R, CE> {

    Optional<R> findById(Long id);
    R create(CE createEditDto);
    Optional<R> update(Long id, CE createEditDto);
    boolean delete(Long id);
}
