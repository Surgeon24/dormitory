package krakow.university.dormitory.servicies;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.services.ReservationChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ReservationCheckerTest {

    private final LocalDate testDate = LocalDate.of(2025, 6, 1);

    private Reservation createReservation(LocalDate date, Integer start, Integer end) {
        Reservation r = new Reservation();
        r.setReservationDate(date);
        r.setReservationTimeStart(start);
        r.setReservationTimeStop(end);
        return r;
    }

    @Test
    void testIsReserved_trueWhenInsideReservationTime() {
        List<Reservation> reservations = List.of(
                createReservation(testDate, 600, 660) // 10:00 - 11:00
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertTrue(checker.isReserved(testDate, 10, 30)); // 630
    }

    @Test
    void testIsReserved_falseWhenBeforeReservation() {
        List<Reservation> reservations = List.of(
                createReservation(testDate, 600, 660) // 10:00 - 11:00
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertFalse(checker.isReserved(testDate, 9, 59)); // 599
    }

    @Test
    void testIsReserved_falseWhenAfterReservation() {
        List<Reservation> reservations = List.of(
                createReservation(testDate, 600, 660) // 10:00 - 11:00
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertFalse(checker.isReserved(testDate, 11, 0)); // 660
    }

    @Test
    void testIsReserved_falseWhenDifferentDate() {
        List<Reservation> reservations = List.of(
                createReservation(testDate.minusDays(1), 600, 660)
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertFalse(checker.isReserved(testDate, 10, 30));
    }

    @Test
    void testIsReserved_trueWhenOneOfMultipleReservationsMatches() {
        List<Reservation> reservations = List.of(
                createReservation(testDate, 600, 660),
                createReservation(testDate, 700, 760)
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertTrue(checker.isReserved(testDate, 11, 40)); // 700
    }

    @Test
    void testIsReserved_falseWhenStartOrEndIsNull() {
        List<Reservation> reservations = List.of(
                createReservation(testDate, null, 660),
                createReservation(testDate, 600, null),
                createReservation(testDate, null, null)
        );
        ReservationChecker checker = new ReservationChecker(reservations);

        assertFalse(checker.isReserved(testDate, 10, 30));
    }
}

