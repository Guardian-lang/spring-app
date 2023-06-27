package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("select a from Admin a " +
            "where a.id = :id")
    Optional<Admin> findById(@Param("id") Long id);

    @Query("select a from Admin a " +
            "where a.first_name = :first_name and a.last_name = :last_name")
    Optional<Admin> findByFL(@Param("first_name") String first_name, @Param("last_name") String last_name);
}
