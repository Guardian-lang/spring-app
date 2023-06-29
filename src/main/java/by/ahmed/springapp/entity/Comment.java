package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    private LocalDate date;
    private String text;
}
