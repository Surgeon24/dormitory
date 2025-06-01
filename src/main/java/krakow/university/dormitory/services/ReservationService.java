package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getUserReservationsById(Integer userId) {
        return reservationRepository.findByReservationUserIdAndReservationIsActiveOrderByReservationDateDescReservationTimeStartDesc(userId, 1);
    }
//    public List<Reservation> getUserReservationsById(Integer userId) {
//        return reservationRepository.findWithRoomByReservationUserId(userId);
//    }

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
}
