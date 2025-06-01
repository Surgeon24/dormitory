package krakow.university.dormitory.dto;

import krakow.university.dormitory.entities.Room;

public class RoomDTO {
    private Integer id;
    private String name;
    private String function;
    private Integer maxCapacity;
    private Boolean isActive;
    private Integer buildingId;

    public void setId(Integer roomId) {
        this.id = roomId;
    }

    public void setName(String roomName) {
        this.name = roomName;
    }

    public void setFunction(String roomFunction) {
        this.function = roomFunction;
    }

    public void setMaxCapacity(Integer roomMaxCapacity) {
        this.maxCapacity = roomMaxCapacity;
    }

    public void setIsActive(boolean b) {
        this.isActive = b;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public static RoomDTO from(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getRoomId());
        dto.setName(room.getRoomName());
        dto.setBuildingId(room.getRoomBuildingId());
        dto.setFunction(room.getRoomFunction());
        // ...
        return dto;
    }
}

