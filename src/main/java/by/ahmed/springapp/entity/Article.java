package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, announce, full_text;
    private Date date;
    private Integer views;
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
}
