package by.ahmed.springapp.repository;

import by.ahmed.springapp.entity.User;
import by.ahmed.springapp.filter.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static by.ahmed.springapp.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getFirst_name(), user.first_name::containsIgnoreCase)
                .add(filter.getLast_name(), user.last_name::containsIgnoreCase)
                .add(filter.getBirth_date(), user.birth_date::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
