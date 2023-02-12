import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryTest {

    UserRepository userRepository;

    @Test
    public void getAllUserByNull() {
        UserRepository userRepository = new UserRepository();
        List<User> expected = userRepository.getAllUser().stream().toList();
        Assertions.assertEquals(expected, new ArrayList<User>());
    }

    @Test
    public void getAllUser() {
        UserRepository userRepository = new UserRepository();
        User user = new User("Dallas", "1234");
        User user1 = new User("Baron", "Mfc");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        List<User> expected = new ArrayList<User>();
        expected.add(user);
        expected.add(user1);
        List<User> actual = (List<User>) userRepository.getAllUser();
       Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchUserByLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test").get();
        Assertions.assertEquals(userTest, user);
    }

    @Test
    public void getNullUserByLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test1").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUserByPasswordNotEqualsLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLoginAndPassword("NotTest", "test").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUserByLoginNotEqualsPassword() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLoginAndPassword("test", "NotTest").orElse(null);
        Assertions.assertNull(user);
    }


}
