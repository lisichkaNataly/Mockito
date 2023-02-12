package org.example;

import java.util.*;

public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
    }

    public Collection<User> getAllUser() {
        return users;
    }

    public Optional <User> getUserByLoginAndPassword(String login, String  password) {
        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> getUserByLogin(String login) {
        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
