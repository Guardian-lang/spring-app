package by.ahmed.springapp.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Authentication {
    @OneToOne(fetch = FetchType.LAZY)
    private Author author;
    private String email, password;
}
