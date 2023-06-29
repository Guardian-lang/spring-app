package by.ahmed.springapp.entity;

import by.ahmed.springapp.validator.annotation.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Email
    private String email;
    @Password
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
