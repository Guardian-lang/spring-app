package by.ahmed.springapp.filter;

import by.ahmed.springapp.entity.Authentication;
import lombok.Value;

import java.util.Date;

@Value
public class AuthorFilter {
        String firstname;
        String lastname;
        Date birthDate;
        Authentication authentication;
}
