package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();

        user.setUserId(1);
        user.setUserName("Ivan");
        user.setUserSurname("Ivanov");
        user.setUserEmail("ivan@example.com");
        user.setUserIsActive(1);
        user.setUserPassword("secret");

        // Проверяем сеттеры и геттеры
        assertThat(user.getUserId()).isEqualTo(1);
        assertThat(user.getUserName()).isEqualTo("Ivan");
        assertThat(user.getUserSurname()).isEqualTo("Ivanov");
        assertThat(user.getUserEmail()).isEqualTo("ivan@example.com");
        assertThat(user.getUserIsActive()).isEqualTo(1);
        assertThat(user.getUserPassword()).isEqualTo("secret");

        // Проверяем дополнительный сеттер setEmail
        user.setEmail("ivan.new@example.com");
        assertThat(user.getUserEmail()).isEqualTo("ivan.new@example.com");
    }

    @Test
    void testToString() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Ivan");
        user.setUserSurname("Ivanov");
        user.setUserEmail("ivan@example.com");
        user.setUserIsActive(1);
        user.setUserPassword("secret");

        String expected = "User{" +
                "userId=1" +
                ", userName='Ivan'" +
                ", userSurname='Ivanov'" +
                ", userEmail='ivan@example.com'" +
                ", userIsActive=1" +
                ", userPassword='secret'" +
                '}';

        assertThat(user.toString()).isEqualTo(expected);
    }
}

