package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Author;
import by.ahmed.springapp.filter.AuthorFilter;

import java.util.List;

public interface FilterAuthorRepository {
    List<Author> findAllByFilter(AuthorFilter filter);
}
