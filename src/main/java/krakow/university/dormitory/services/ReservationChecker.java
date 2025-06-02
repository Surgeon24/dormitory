package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public class ReservationChecker {
    private final List<Reservation> reservations;

    public ReservationChecker(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isReserved(LocalDate date, int hour, int minute) {
        int timeInMinutes = hour * 60 + minute;

        for (Reservation r : reservations) {
            if (r.getReservationDate().equals(date)) {
                Integer start = r.getReservationTimeStart();
                Integer end = r.getReservationTimeStop();
                if (start != null && end != null && timeInMinutes >= start && timeInMinutes < end) {
                    return true;
                }
            }
        }
        return false;
    }
}


