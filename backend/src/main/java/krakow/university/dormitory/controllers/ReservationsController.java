package krakow.university.dormitory.controllers;

import krakow.university.dormitory.dto.ReservationDTO;
import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/my")
    public List<ReservationDTO> myReservations(Principal principal) {
        User currentUser = userService.findByUserEmail(principal.getName());
        Integer userId = currentUser.getUserId();
        List<Reservation> reservations = reservationService.getUserReservationsById(userId);
        return reservations.stream()
                .map(ReservationDTO::from)
                .toList();
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelReservation(@RequestParam("reservationId") Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
