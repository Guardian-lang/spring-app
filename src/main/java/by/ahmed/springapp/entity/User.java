package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(schema = "public", name = "user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String first_name;
    private String last_name;
    private LocalDate birth_date;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String job_title;
    @Embedded
    private Authentication authentication;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Article> articles;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
}
