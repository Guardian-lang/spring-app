package by.ahmed.springapp.entity;

import by.ahmed.springapp.validator.annotation.UserPassword;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Embeddable
@Getter
@Setter
@FieldNameConstants
public class UserAuthentication {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Email
    private String email;
    @UserPassword
    private String password;
}
