package krakow.university.dormitory.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservations {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "reservation_user_id")
    private Integer userId;

    @Column(name = "reservation_room_id")
    private Integer roomId;

    @Column(name = "reservation_date")
    private LocalDate date;

    @Column(name = "reservation_time_start")
    private Integer timeStart;

    @Column(name = "reservation_time_stop")
    private Integer timeStop;

    @Column(name = "reservation_is_active")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "reservation_user_id", insertable = false, updatable = false)
    private User user;

    public Integer getReservationId() { return reservationId; }
    public Integer getUserId() { return userId; }
    public Integer getRoomId() { return roomId; }
    public LocalDate getDate() { return date; }
    public Integer getTimeStart() { return timeStart; }
    public Integer getTimeStop() { return timeStop; }
    public Integer getIsActive() { return isActive; }
    public User getUser() { return user; }

}
