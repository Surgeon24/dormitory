package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoomAvailabilityTest {

    @Test
    void testGettersAndSetters() {
        RoomAvailability roomAvailability = new RoomAvailability();

        RoomAvailabilityId id = new RoomAvailabilityId();
        id.setRoomId(10);
        id.setAvailabilityId(20);
        roomAvailability.setId(id);

        roomAvailability.setRoomId(10);
        roomAvailability.setAvailabilityId(20);


        Room room = new Room();
        room.setRoomId(10);
        room.setRoomName("Room A");

        Availability availability = new Availability();
        availability.setAvailabilityId(20);
        availability.setAvailabilityWeekday(1);

        roomAvailability.setRoom(room);
        roomAvailability.setAvailability(availability);

        assertThat(roomAvailability.getId()).isEqualTo(id);
        assertThat(roomAvailability.getRoomId()).isEqualTo(10);
        assertThat(roomAvailability.getAvailabilityId()).isEqualTo(20);
        assertThat(roomAvailability.getRoom()).isEqualTo(room);
        assertThat(roomAvailability.getAvailability()).isEqualTo(availability);
    }

    @Test
    void testToString() {
        RoomAvailabilityId id = new RoomAvailabilityId();
        id.setRoomId(10);
        id.setAvailabilityId(20);

        Room room = new Room();
        room.setRoomId(10);
        room.setRoomName("Room A");

        Availability availability = new Availability();
        availability.setAvailabilityId(20);
        availability.setAvailabilityWeekday(1);

        RoomAvailability roomAvailability = new RoomAvailability();
        roomAvailability.setId(id);
        roomAvailability.setRoomId(10);
        roomAvailability.setAvailabilityId(20);
        roomAvailability.setRoom(room);
        roomAvailability.setAvailability(availability);

        String expected = "RoomAvailability{" +
                "id=" + id.toString() +
                ", availabilityId=20" +
                ", roomId=10" +
                ", room=" + room.toString() +
                ", availability=" + availability.toString() +
                '}';

        assertThat(roomAvailability.toString()).isEqualTo(expected);
    }
}
