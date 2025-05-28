package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Reservations;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/")
    public String home(Model model) {
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
    public String facilities() {
        return "facilities";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/reservations")
    public String myReservations(Model model, Principal principal) {
        System.out.println("Logged in as: " + principal.getName());
        User currentUser = userService.getByEmail(principal.getName());
        System.out.println("current user: " + currentUser);
        Integer userId = currentUser.getUserId();
        System.out.println("current user id: " + userId);
        List<Reservations> reservations = reservationService.getUserReservationsById(userId);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }


    @GetMapping("/news")
    public String news() {
        return "news";
    }
}