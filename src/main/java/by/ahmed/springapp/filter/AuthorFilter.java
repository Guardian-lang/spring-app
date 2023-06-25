package by.ahmed.springapp.filter;

import java.util.Date;

public record AuthorFilter(String firstname,
                           String lastname,
                           Date birthDate) {
        }
