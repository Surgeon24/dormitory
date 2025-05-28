package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.Reservations;
import krakow.university.dormitory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findByUser(User user);
    List<Reservations> findByUserId(Integer userId);
}
