package krakow.university.dormitory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms_availabilities")
public class RoomAvailability {

    @EmbeddedId
    private RoomAvailabilityId id;

    @Column(name = "availability_id")
    private Integer availabilityId;

    @Column(name = "room_id")
    private Integer roomId;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @MapsId("availabilityId")
    @JoinColumn(name = "availability_id", insertable = false, updatable = false)
    private Availability availability;

    public RoomAvailabilityId getId() {
        return id;
    }

    public void setId(RoomAvailabilityId id) {
        this.id = id;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "RoomAvailability{" +
                "id=" + id +
                ", availabilityId=" + availabilityId +
                ", roomId=" + roomId +
                ", room=" + room +
                ", availability=" + availability +
                '}';
    }
}

