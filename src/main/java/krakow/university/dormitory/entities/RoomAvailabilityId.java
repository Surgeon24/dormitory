package krakow.university.dormitory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomAvailabilityId implements Serializable {
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "availability_id")
    private Integer availabilityId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    @Override
    public String toString() {
        return "RoomAvailabilityId{" +
                "roomId=" + roomId +
                ", availabilityId=" + availabilityId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomAvailabilityId)) return false;
        RoomAvailabilityId that = (RoomAvailabilityId) o;
        return Objects.equals(roomId, that.roomId) &&
                Objects.equals(availabilityId, that.availabilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, availabilityId);
    }
}
