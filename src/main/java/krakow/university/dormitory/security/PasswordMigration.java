package krakow.university.dormitory.security;

import jakarta.transaction.Transactional;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class PasswordMigration {

    //It was run once to hash all the passwords. DO NOT run again by uncommenting @Bean when passwords are already hashed!
    //@Bean
    public CommandLineRunner encryptPlaintextPasswords(UserRepository userRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            List<User> users = userRepository.findAll();

            for (User user : users) {
                String rawPassword = user.getUserPassword();
                if (!isBCryptHashed(rawPassword)) {
                    String hashed = encoder.encode(rawPassword);
                    user.setUserPassword(hashed);
                    System.out.println("Updated password for user: " + user.getUserEmail());
                }
            }

            userRepository.saveAll(users);
        };
    }

    private boolean isBCryptHashed(String password) {
        return password != null && password.startsWith("$2a$") && password.length() == 60;
    }
}
