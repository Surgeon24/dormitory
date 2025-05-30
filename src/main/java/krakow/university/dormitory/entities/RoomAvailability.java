package krakow.university.dormitory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms_availabilities")
public class RoomAvailability {

    @EmbeddedId
    private RoomAvailabilityId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @MapsId("availabilityId")
    @JoinColumn(name = "availability_id")
    private Availability availability;

    public RoomAvailabilityId getId() {
        return id;
    }

    public void setId(RoomAvailabilityId id) {
        this.id = id;
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
                ", room=" + room +
                ", availability=" + availability +
                '}';
    }
}

