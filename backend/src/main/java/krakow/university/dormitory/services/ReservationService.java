package krakow.university.dormitory.services;

import krakow.university.dormitory.dto.ReservationRequestDTO;
import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.ReservationRepository;
import krakow.university.dormitory.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Reservation> getUserReservationsById(Integer userId) {
        return reservationRepository.findByReservationUserIdAndReservationIsActiveOrderByReservationDateDescReservationTimeStartDesc(userId, 1);
    }

    public List<Reservation> getReservationsByRoomId(Integer roomId) {
        return reservationRepository.findByReservationRoomId(roomId);
    }

    public Reservation createReservation(Integer userId, Integer roomId, LocalDate date, Integer start, Integer stop) {
        Reservation reservation = new Reservation();
        reservation.setReservationUserId(userId);
        reservation.setReservationRoomId(roomId);
        reservation.setReservationDate(date);
        reservation.setReservationTimeStart(start);
        reservation.setReservationTimeStop(stop);
        reservation.setReservationIsActive(1);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Integer reservationId) {
        reservationRepository.deactivateReservationById(reservationId);
    }

    public void validateAndCreateReservation(Integer roomId, ReservationRequestDTO request) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null || room.getRoomIsActive() == 0) {
            throw new IllegalArgumentException("Room not found or inactive.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(request.getReservationDate());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot make reservations in the past.");
        }

        int start = parseTimeToInt(request.getStartTime());
        int stop = parseTimeToInt(request.getEndTime());

        if (start >= stop) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        List<Reservation> existing = reservationRepository.findByReservationRoomId(roomId).stream()
                .filter(r -> r.getReservationDate().equals(date) && r.getReservationIsActive() == 1)
                .collect(Collectors.toList());

        boolean conflict = existing.stream().anyMatch(r ->
                !(stop <= r.getReservationTimeStart() || start >= r.getReservationTimeStop())
        );

        if (conflict) {
            throw new IllegalArgumentException("The selected time slot is already reserved.");
        }

        createReservation(request.getUserId(), roomId, date, start, stop);
    }

    private int parseTimeToInt(String time) {
        return Integer.parseInt(time.replace(":", ""));
    }
}

