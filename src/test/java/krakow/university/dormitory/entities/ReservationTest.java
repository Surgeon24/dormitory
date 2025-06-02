package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

public class ReservationTest {

    @Test
    void testGettersAndSetters() {
        Reservation reservation = new Reservation();

        User user = new User();
        user.setUserId(1);
        user.setUserName("John Doe");

        Room room = new Room();
        room.setRoomId(100);
        room.setRoomName("Conference Room");

        reservation.setReservationId(10);
        reservation.setReservationUserId(1);
        reservation.setReservationRoomId(100);
        reservation.setReservationDate(LocalDate.of(2025, 6, 2));
        reservation.setReservationTimeStart(9);
        reservation.setReservationTimeStop(11);
        reservation.setReservationIsActive(1);
        reservation.setReservationUser(user);
        reservation.setReservationRoom(room);

        assertThat(reservation.getReservationId()).isEqualTo(10);
        assertThat(reservation.getReservationUserId()).isEqualTo(1);
        assertThat(reservation.getReservationRoomId()).isEqualTo(100);
        assertThat(reservation.getReservationDate()).isEqualTo(LocalDate.of(2025, 6, 2));
        assertThat(reservation.getReservationTimeStart()).isEqualTo(9);
        assertThat(reservation.getReservationTimeStop()).isEqualTo(11);
        assertThat(reservation.getReservationIsActive()).isEqualTo(1);
        assertThat(reservation.getReservationUser()).isEqualTo(user);
        assertThat(reservation.getReservationRoom()).isEqualTo(room);
    }

    @Test
    void testToString() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("John Doe");

        Room room = new Room();
        room.setRoomId(100);
        room.setRoomName("Conference Room");

        Reservation reservation = new Reservation();
        reservation.setReservationId(10);
        reservation.setReservationUserId(1);
        reservation.setReservationRoomId(100);
        reservation.setReservationDate(LocalDate.of(2025, 6, 2));
        reservation.setReservationTimeStart(9);
        reservation.setReservationTimeStop(11);
        reservation.setReservationIsActive(1);
        reservation.setReservationUser(user);
        reservation.setReservationRoom(room);

        String expected = "Reservation{reservationId=10, reservationUserId=1, reservationRoomId=100, reservationDate=2025-06-02, " +
                "reservationTimeStart=9, reservationTimeStop=11, reservationIsActive=1, reservationUser=" + user.toString() + ", " +
                "reservationRoom=" + room.toString() + "}";

        assertThat(reservation.toString()).isEqualTo(expected);
    }
}

