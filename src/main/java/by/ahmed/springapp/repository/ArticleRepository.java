package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a " +
            "where a.title = :title ")
    Article findByTitle(@Param("title") String title);

    @Query("select a from Article a " +
            "where a.date between :date1 and :date2")
    List<Article> sortByDate(@Param("date1") Date date1, @Param("date2") Date date2);

    @Query("select a from Article a " +
            "where a.id = :id")
    Optional<Article> findById(@Param("id") Long id);

//    Page<Article> findFirstNArticles(Pageable pageable, Integer n);
}
