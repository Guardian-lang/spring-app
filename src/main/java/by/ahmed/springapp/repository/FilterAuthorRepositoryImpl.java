package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Author;
import by.ahmed.springapp.filter.AuthorFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static by.ahmed.springapp.entity.QAuthor.author;

@RequiredArgsConstructor
public class FilterAuthorRepositoryImpl implements FilterAuthorRepository{

    private final EntityManager entityManager;

    @Override
    public List<Author> findAllByFilter(AuthorFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), author.first_name::containsIgnoreCase)
                .add(filter.lastname(), author.last_name::containsIgnoreCase)
                .add(filter.birthDate(), author.birth_date::before)
                .build();

        return new JPAQuery<Author>(entityManager)
                .select(author)
                .from(author)
                .where(predicate)
                .fetch();
    }
}
