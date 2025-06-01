package krakow.university.dormitory.controllers;

import krakow.university.dormitory.dto.ReservationRequestDTO;
import krakow.university.dormitory.dto.RoomDetailsDTO;
import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.entities.RoomAvailability;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.RoomAvailabilityService;
import krakow.university.dormitory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<RoomDetailsDTO> getRoomDetails(
            @PathVariable Integer id,
            @RequestParam(name = "week", defaultValue = "current") String week) {

        Room room = roomService.getRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        RoomDetailsDTO dto = roomService.buildRoomDetailsDTO(room, week);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<?> createReservation(
            @PathVariable Integer id,
            @RequestBody ReservationRequestDTO request) {

        try {
            reservationService.validateAndCreateReservation(id, request);
            return ResponseEntity.ok("Reservation created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

