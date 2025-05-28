package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.Reservations;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservations> getUserReservations(User user) {
        return reservationRepository.findByUser(user);
    }

    public List<Reservations> getUserReservationsById(Integer userId) {
        return reservationRepository.findByUserId(userId);
    }
}
