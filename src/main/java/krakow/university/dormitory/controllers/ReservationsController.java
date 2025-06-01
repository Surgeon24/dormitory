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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ReservationsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    public ReservationsController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public String myReservations(@RequestParam(name = "showFinished", defaultValue = "false") boolean showFinished,
                                 Model model, Principal principal) {
        User currentUser = userService.findByUserEmail(principal.getName());
        Integer userId = currentUser.getUserId();

        List<Reservation> allReservations = reservationService.getUserReservationsById(userId);

        if (!showFinished) {
            LocalDateTime now = LocalDateTime.now();
            allReservations = allReservations.stream()
                    .filter(res -> {
                        LocalTime endTime = LocalTime.of(res.getReservationTimeStop() / 60, res.getReservationTimeStop() % 60);
                        LocalDateTime resEnd = LocalDateTime.of(res.getReservationDate(), endTime);
                        return resEnd.isAfter(now);
                    })
                    .toList();
        }

        model.addAttribute("reservations", allReservations);
        model.addAttribute("showFinished", showFinished); // чтобы кнопка отображалась корректно
        return "reservations";
    }


    @PostMapping("/cancel-reservation")
    public String cancelReservation(@RequestParam("reservationId") Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservations";
    }
}
