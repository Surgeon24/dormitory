package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvailabilityTest {

    @Test
    void testGettersAndSetters() {
        Availability availability = new Availability();

        availability.setAvailabilityId(1);
        availability.setAvailabilityWeekday(2);
        availability.setAvailabilityStartHour(9);
        availability.setAvailabilityStopHour(17);

        assertThat(availability.getAvailabilityId()).isEqualTo(1);
        assertThat(availability.getAvailabilityWeekday()).isEqualTo(2);
        assertThat(availability.getAvailabilityStartHour()).isEqualTo(9);
        assertThat(availability.getAvailabilityStopHour()).isEqualTo(17);
    }

    @Test
    void testToString() {
        Availability availability = new Availability();
        availability.setAvailabilityId(1);
        availability.setAvailabilityWeekday(2);
        availability.setAvailabilityStartHour(9);
        availability.setAvailabilityStopHour(17);

        String expected = "Availability{availabilityId=1, availabilityWeekday=2, availabilityStartHour=9, availabilityStopHour=17}";
        assertThat(availability.toString()).isEqualTo(expected);
    }
}
