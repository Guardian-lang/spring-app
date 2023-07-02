package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select img from Image img " +
            "left join Article a " +
            "where a.title = :title")
    List<Image> findImageByArticle(@Param("title") String title);

    @Query("select img from Image img " +
            "left join User u " +
            "where u.username = :user")
    Optional<Image> findImageByUsername(@Param("username") String username);
}
