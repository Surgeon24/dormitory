package krakow.university.dormitory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @Column(name = "room_building_id")
    private Integer roomBuildingId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_function")
    private String roomFunction;

    @Column(name = "room_max_capacity")
    private Integer roomMaxCapacity;

    @Column(name = "room_is_active")
    private Integer roomIsActive;

    @ManyToOne
    @JoinColumn(name = "room_building_id", insertable = false, updatable = false)
    private Building roomBuilding;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomBuildingId() {
        return roomBuildingId;
    }

    public void setRoomBuildingId(Integer roomBuildingId) {
        this.roomBuildingId = roomBuildingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomFunction() {
        return roomFunction;
    }

    public void setRoomFunction(String roomFunction) {
        this.roomFunction = roomFunction;
    }

    public Integer getRoomMaxCapacity() {
        return roomMaxCapacity;
    }

    public void setRoomMaxCapacity(Integer roomMaxCapacity) {
        this.roomMaxCapacity = roomMaxCapacity;
    }

    public Integer getRoomIsActive() {
        return roomIsActive;
    }

    public void setRoomIsActive(Integer roomIsActive) {
        this.roomIsActive = roomIsActive;
    }

    public Building getRoomBuilding() {
        return roomBuilding;
    }

    public void setRoomBuilding(Building roomBuilding) {
        this.roomBuilding = roomBuilding;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomBuildingId=" + roomBuildingId +
                ", roomName='" + roomName + '\'' +
                ", roomFunction='" + roomFunction + '\'' +
                ", roomMaxCapacity=" + roomMaxCapacity +
                ", roomIsActive=" + roomIsActive +
                ", roomBuilding=" + roomBuilding +
                '}';
    }

    public void setId(int roomId) {
        this.roomId = roomId;
    }
}
