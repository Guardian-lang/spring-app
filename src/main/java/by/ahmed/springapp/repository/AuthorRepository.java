package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("select a from Author a " +
            "where a.first_name = :firstName " +
            "and a.last_name = :lastName")
    Author findAuthorByFI(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select au from Author au " +
            "left join Article ar " +
            "where ar.title = :title")
    Author findAuthorByArticle(@Param("title") String title);
}
