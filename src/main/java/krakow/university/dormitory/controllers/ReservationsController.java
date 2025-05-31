package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public String myReservations(Model model, Principal principal) {
        User currentUser = userService.findByUserEmail(principal.getName());
        Integer userId = currentUser.getUserId();
        List<Reservation> reservations = reservationService.getUserReservationsById(userId);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @PostMapping("/cancel-reservation")
    public String cancelReservation(@RequestParam("reservationId") Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservations";
    }
}
