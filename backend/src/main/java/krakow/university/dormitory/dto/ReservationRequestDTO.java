package krakow.university.dormitory.dto;

public class ReservationRequestDTO {
    private Integer userId;
    private String reservationDate; // yyyy-MM-dd
    private String startTime;       // HH:mm
    private String endTime;         // HH:mm

    public String getReservationDate() {
        return reservationDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getUserId() {
        return userId;
    }
}

