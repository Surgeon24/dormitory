package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoomTest {

    @Test
    void testGettersAndSetters() {
        Room room = new Room();

        Building building = new Building();
        building.setBuildingId(5);
        building.setBuildingName("Main Building");
        building.setBuildingAddress("123 Street");
        building.setBuildingIsActive(1);

        room.setRoomId(10);
        room.setRoomBuildingId(5);
        room.setRoomName("Conference Room");
        room.setRoomFunction("Meeting");
        room.setRoomMaxCapacity(50);
        room.setRoomIsActive(1);
        room.setRoomBuilding(building);

        assertThat(room.getRoomId()).isEqualTo(10);
        assertThat(room.getRoomBuildingId()).isEqualTo(5);
        assertThat(room.getRoomName()).isEqualTo("Conference Room");
        assertThat(room.getRoomFunction()).isEqualTo("Meeting");
        assertThat(room.getRoomMaxCapacity()).isEqualTo(50);
        assertThat(room.getRoomIsActive()).isEqualTo(1);
        assertThat(room.getRoomBuilding()).isEqualTo(building);
    }

    @Test
    void testToString() {
        Building building = new Building();
        building.setBuildingId(5);
        building.setBuildingName("Main Building");
        building.setBuildingAddress("123 Street");
        building.setBuildingIsActive(1);

        Room room = new Room();
        room.setRoomId(10);
        room.setRoomBuildingId(5);
        room.setRoomName("Conference Room");
        room.setRoomFunction("Meeting");
        room.setRoomMaxCapacity(50);
        room.setRoomIsActive(1);
        room.setRoomBuilding(building);

        String expected = "Room{roomId=10, roomBuildingId=5, roomName='Conference Room', roomFunction='Meeting', roomMaxCapacity=50, roomIsActive=1, roomBuilding=" + building.toString() + "}";
        assertThat(room.toString()).isEqualTo(expected);
    }
}

