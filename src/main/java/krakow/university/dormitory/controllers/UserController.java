package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("activePage", "home");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/facilities")
    public String facilities(Model model) {
        model.addAttribute("activePage", "facilities");
        return "facilities";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("activePage", "info");
        return "info";
    }

    @GetMapping("/reservations")
    public String reservations(Model model) {
        model.addAttribute("activePage", "reservations");
        return "reservations";
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("activePage", "news");
        return "news";
    }
}