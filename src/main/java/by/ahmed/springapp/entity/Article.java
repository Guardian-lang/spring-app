package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title, announce, full_text;
    private Date date;
    private Integer views;
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comments> comments;
}