package krakow.university.dormitory.servicies;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

import krakow.university.dormitory.entities.RoomAvailability;
import krakow.university.dormitory.repositories.RoomAvailabilityRepository;
import krakow.university.dormitory.services.RoomAvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomAvailabilityServiceTest {

    @Mock
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @InjectMocks
    private RoomAvailabilityService roomAvailabilityService;

    private RoomAvailability availability;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        availability = new RoomAvailability();
        availability.setRoomId(1);
    }

    @Test
    void testGetRoomAvailabilitiesByRoomId() {
        when(roomAvailabilityRepository.findByRoomId(1)).thenReturn(List.of(availability));

        List<RoomAvailability> result = roomAvailabilityService.getRoomAvailabilitiesByRoomId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        verify(roomAvailabilityRepository, times(1)).findByRoomId(1);
    }
}
