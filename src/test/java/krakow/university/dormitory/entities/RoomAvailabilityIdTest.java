package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoomAvailabilityIdTest {

    @Test
    void testGettersAndSetters() {
        RoomAvailabilityId id = new RoomAvailabilityId();

        id.setRoomId(10);
        id.setAvailabilityId(20);

        assertThat(id.getRoomId()).isEqualTo(10);
        assertThat(id.getAvailabilityId()).isEqualTo(20);
    }

    @Test
    void testEqualsAndHashCode() {
        RoomAvailabilityId id1 = new RoomAvailabilityId();
        id1.setRoomId(10);
        id1.setAvailabilityId(20);

        RoomAvailabilityId id2 = new RoomAvailabilityId();
        id2.setRoomId(10);
        id2.setAvailabilityId(20);

        RoomAvailabilityId id3 = new RoomAvailabilityId();
        id3.setRoomId(11);
        id3.setAvailabilityId(21);

        assertThat(id1).isEqualTo(id2);
        assertThat(id1.hashCode()).isEqualTo(id2.hashCode());

        assertThat(id1).isNotEqualTo(id3);
    }

    @Test
    void testToString() {
        RoomAvailabilityId id = new RoomAvailabilityId();
        id.setRoomId(10);
        id.setAvailabilityId(20);

        String expected = "RoomAvailabilityId{roomId=10, availabilityId=20}";
        assertThat(id.toString()).isEqualTo(expected);
    }
}

