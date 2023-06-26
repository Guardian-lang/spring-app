package by.ahmed.springapp.filter;

import by.ahmed.springapp.entity.Authentication;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Value
@Getter
public class AuthorFilter {
        String first_name;
        String last_name;
        Date birth_date;
        Authentication authentication;
}
