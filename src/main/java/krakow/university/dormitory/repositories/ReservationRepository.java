package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationUserId(Integer userId);
    List<Reservation> findByReservationRoomId(Integer userId);
    List<Reservation> findByReservationUserIdAndReservationIsActiveOrderByReservationDateDescReservationTimeStartDesc(Integer userId, Integer active);

    <S extends Reservation> S save(S reservation);

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.reservationIsActive = 0 WHERE r.reservationId = :reservationId")
    void deactivateReservationById(Integer reservationId);
}
