package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
    @Embedded
    private UserAuthentication authentication;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comments> comments;
}
