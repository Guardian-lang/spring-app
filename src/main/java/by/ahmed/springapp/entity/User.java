package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
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
    @NotNull
    private String username;
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Image avatar;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private LocalDate birth_date;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private String job_title;
    @NotNull
    @Embedded
    private Authentication authentication;
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Article> articles;
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
