package by.ahmed.springapp.entity;

import by.ahmed.springapp.validator.annotation.Password;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Embeddable
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    @OneToOne(fetch = FetchType.LAZY)
    private Author author;
    @Email
    private String email;
    @Password
    private String password;
}
