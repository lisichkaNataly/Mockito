package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLoginByUser(String login) {
        return userRepository.getAllUser().stream().map(User::getLogin).collect(Collectors.toList());
    }

    public void addUser(String login, String password)  {
        User user = new User(login, password);
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("User login should be defined");
        }
        boolean userExist = this.userRepository.
                getAllUser().
                stream().
                anyMatch(t -> equals(user));
        if (userExist) {
            throw new UserNonUniqueException();
        }
        this.userRepository.addUser(user);
    }

}
