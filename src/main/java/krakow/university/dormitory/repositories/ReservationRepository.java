package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationUser(User user);
    List<Reservation> findByReservationUserId(Integer userId);
}
