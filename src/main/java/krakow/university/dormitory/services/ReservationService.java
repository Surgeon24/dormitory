package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getUserReservations(User user) {
        return reservationRepository.findByReservationUser(user);
    }

    public List<Reservation> getUserReservationsById(Integer userId) {
        return reservationRepository.findByReservationUserId(userId);
    }
}
