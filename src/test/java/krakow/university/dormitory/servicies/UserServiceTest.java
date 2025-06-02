package krakow.university.dormitory.servicies;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.UserRepository;
import krakow.university.dormitory.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleUser = new User();
        sampleUser.setUserEmail("test@example.com");
        sampleUser.setUserId(1);
    }

    @Test
    void testFindByUserEmail_UserFound() {
        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.of(sampleUser));

        User result = userService.findByUserEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getUserEmail());
        verify(userRepository, times(1)).findByUserEmail("test@example.com");
    }

    @Test
    void testFindByUserEmail_UserNotFound() {
        when(userRepository.findByUserEmail("missing@example.com")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.findByUserEmail("missing@example.com")
        );

        assertEquals("User with email missing@example.com not found", exception.getMessage());
        verify(userRepository, times(1)).findByUserEmail("missing@example.com");
    }
}
