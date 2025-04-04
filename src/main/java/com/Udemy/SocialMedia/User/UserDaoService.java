package com.Udemy.SocialMedia.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 1;
    static {
        users.add(new User(usersCount++, "Raj", LocalDate.now().minusYears(18)));
        users.add(new User(usersCount++, "Sid", LocalDate.now().minusYears(19)));
        users.add(new User(usersCount++, "Dinesh", LocalDate.now().minusYears(21)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        user.setId(usersCount++);
        users.add(user);
    }

    public void deleteUserById(int id) {
        users.removeIf(user -> user.getId() == id);
    }

}
