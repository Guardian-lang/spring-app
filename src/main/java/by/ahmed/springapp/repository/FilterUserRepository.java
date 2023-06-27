package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.User;
import by.ahmed.springapp.filter.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
