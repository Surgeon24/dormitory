package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}