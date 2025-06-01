package krakow.university.dormitory.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RoomDetailsDTO {
    private RoomDTO room;
    private String roomFunction;
    private List<AvailabilityDTO> availabilities;
    private List<ReservationDTO> reservations;
    private Map<Integer, String> weekdayNames;
    private Map<Integer, String> availabilityStartTimes;
    private Map<Integer, String> availabilityStopTimes;
    private Map<Integer, String> reservationStartTimes;
    private Map<Integer, String> reservationStopTimes;
    private String selectedWeek;
    private List<LocalDate> allowedDates;

    public void setRoom(RoomDTO roomDTO) {
        this.room = roomDTO;
    }

    public void setRoomFunction(String roomFunction) {
        this.roomFunction = roomFunction;
    }

    public void setAllowedDates(List<LocalDate> allowedDates) {
        this.allowedDates = allowedDates;
    }

    public void setSelectedWeek(String week) {
        this.selectedWeek = week;
    }

    public void setAvailabilities(List<AvailabilityDTO> availabilities) {
        this.availabilities = availabilities;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public void setWeekdayNames(Map<Integer,String> weekdayNames) {
        this.weekdayNames = weekdayNames;
    }

    public void setAvailabilityStartTimes(Map<Integer,String> timeRangeMap) {
        this.availabilityStartTimes = timeRangeMap;
    }

    public void setAvailabilityStopTimes(Map<Integer, String> timeRangeMap) {
        this.availabilityStopTimes = timeRangeMap;
    }

    public void setReservationStartTimes(Map<Integer, String> timeRangeMap) {
        this.reservationStartTimes = timeRangeMap;
    }

    public void setReservationStopTimes(Map<Integer, String> timeRangeMap) {
        this.reservationStopTimes = timeRangeMap;
    }
}

