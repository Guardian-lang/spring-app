package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select com from Comment com " +
            "where com.id = :id")
    Optional<Comment> findById(@Param("id") Long id);

    @Query("select com from Comment com " +
            "left join User u " +
            "where u.username = :username")
    List<Comment> findCommentsOfUserByUsername(@Param("username") String username);

    @Query("select com from Comment com " +
            "left join Article ar " +
            "where ar.title = :title")
    List<Comment> findCommentInArticleByTitle(@Param("title") String title);
}
