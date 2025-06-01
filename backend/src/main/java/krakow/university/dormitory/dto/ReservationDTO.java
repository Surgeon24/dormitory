package krakow.university.dormitory.dto;

import krakow.university.dormitory.entities.Reservation;

import java.time.LocalDate;

public class ReservationDTO {
    private Integer id;
    private String roomName;
    private LocalDate date;
    private Integer startTime;
    private Integer endTime;
    private Integer userId;

    public static ReservationDTO from(Reservation r) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(r.getReservationId());
        dto.setRoomName(r.getReservationRoom().getRoomName());
        dto.setStartTime(r.getReservationTimeStart());
        dto.setEndTime(r.getReservationTimeStop());
        return dto;
    }

    private void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    private void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private void setId(Integer reservationId) {
        this.id = reservationId;
    }

    public void setUserId(Integer reservationUserId) {
        this.userId = reservationUserId;
    }

    public void setDate(LocalDate reservationDate) {
        this.date = reservationDate;
    }

    public void setStopTime(Integer reservationTimeStop) {
        this.endTime = reservationTimeStop;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getStopTime() {
        return endTime;
    }
}
