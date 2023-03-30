package com.ouwesh.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Ouwesh", LocalDate.of(1999, 1, 28)));
        users.add(new User(2, "Raj", LocalDate.of(1988, 1, 1)));
        users.add(new User(3, "Rajesh", LocalDate.of(1988, 1, 1)));
    }

    public List<User> findAll() {
        return users;
    }

    public Integer save(User user) {
        if (user.getId() == null) {
            user.setId(users.size() + 1);
        }
        users.add(user);
        return user.getId();
    }

    public User findOne(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().get();
    }
}
