package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("select u from User u " +
            "where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    List<String> findAllByUsernameNotNull();

    List<String> findAllByEmailNotNull();
}
