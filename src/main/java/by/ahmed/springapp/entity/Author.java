package by.ahmed.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name, last_name, job_title;
    private Date birth_date;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Article> articleList;
}
