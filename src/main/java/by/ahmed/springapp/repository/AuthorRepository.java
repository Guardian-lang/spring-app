package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>,
        FilterAuthorRepository, QuerydslPredicateExecutor<Author> {

    @Query("select a from Author a " +
            "where a.first_name = :firstName " +
            "and a.last_name = :lastName")
    List<Author> findAuthorsByFL(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select au from Author au " +
            "left join Article ar " +
            "where ar.title = :title")
    Author findAuthorByArticle(@Param("title") String title);
}
