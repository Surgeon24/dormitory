package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + userEmail + " not found"));
    }

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerNewUser(RegistrationDTO dto) {
        User user = new User();
        user.setUserEmail(dto.getEmail());
        user.setUserName(dto.getName());
        user.setUserPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByUserEmail(email).isPresent();
    }
}