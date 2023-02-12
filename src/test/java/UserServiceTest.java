import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("When userRepository contains users, then Service returns allLogins")
    void getAllUserList() {
        Assertions.assertNotNull(userRepository);
        Mockito.when(userRepository.getAllUser()).thenReturn(List.of(new User("test", "test")));
        List<String> test = List.of("test");
        Assertions.assertEquals(userService.getAllLoginByUser("test"), test);
    }


    @Test
    @DisplayName("When Login and Password are correct, then Service adds new User")
    void createCorrectUser() {
        Assertions.assertNotNull(userRepository);
        userService.addUser("test", "test");
        Mockito.verify(userRepository).addUser(Mockito.any());
    }


    @Test
    @DisplayName("When Login is empty, then Service throw IllegalArgumentException")
    void notCorrectLogin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addUser(null, "test"));
    }


    @Test
    @DisplayName("When password is empty, then Service throw IllegalArgumentException")
    void notCorrectPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addUser("test", null));
    }


}
