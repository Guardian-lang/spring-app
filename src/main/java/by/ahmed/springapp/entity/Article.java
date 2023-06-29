package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title, announce, full_text;
    private LocalDate date;
    private Integer views;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
}
