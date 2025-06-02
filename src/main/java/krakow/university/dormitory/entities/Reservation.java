package krakow.university.dormitory.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "reservation_user_id")
    private Integer reservationUserId;

    @Column(name = "reservation_room_id")
    private Integer reservationRoomId;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "reservation_time_start")
    private Integer reservationTimeStart;

    @Column(name = "reservation_time_stop")
    private Integer reservationTimeStop;

    @Column(name = "reservation_is_active")
    private Integer reservationIsActive;

    @ManyToOne
    @JoinColumn(name = "reservation_user_id", insertable = false, updatable = false)
    private User reservationUser;

    @ManyToOne
    @JoinColumn(name = "reservation_room_id", referencedColumnName = "room_id", insertable = false, updatable = false)
    private Room reservationRoom;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationUserId() {
        return reservationUserId;
    }

    public void setReservationUserId(Integer reservationUserId) {
        this.reservationUserId = reservationUserId;
    }

    public Integer getReservationRoomId() {
        return reservationRoomId;
    }

    public void setReservationRoomId(Integer reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getReservationTimeStart() {
        return reservationTimeStart;
    }

    public void setReservationTimeStart(Integer reservationTimeStart) {
        this.reservationTimeStart = reservationTimeStart;
    }

    public Integer getReservationTimeStop() {
        return reservationTimeStop;
    }

    public void setReservationTimeStop(Integer reservationTimeStop) {
        this.reservationTimeStop = reservationTimeStop;
    }

    public Integer getReservationIsActive() {
        return reservationIsActive;
    }

    public void setReservationIsActive(Integer reservationIsActive) {
        this.reservationIsActive = reservationIsActive;
    }

    public User getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(User reservationUser) {
        this.reservationUser = reservationUser;
    }

    public Room getReservationRoom() {
        return reservationRoom;
    }

    public void setReservationRoom(Room reservationRoom) {
        this.reservationRoom = reservationRoom;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", reservationUserId=" + reservationUserId +
                ", reservationRoomId=" + reservationRoomId +
                ", reservationDate=" + reservationDate +
                ", reservationTimeStart=" + reservationTimeStart +
                ", reservationTimeStop=" + reservationTimeStop +
                ", reservationIsActive=" + reservationIsActive +
                ", reservationUser=" + reservationUser +
                ", reservationRoom=" + reservationRoom +
                '}';
    }
}
