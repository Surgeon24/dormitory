package krakow.university.dormitory.servicies;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.repositories.RoomRepository;
import krakow.university.dormitory.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private Room sampleRoom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleRoom = new Room();
        sampleRoom.setRoomId(1);
        sampleRoom.setRoomFunction("pralnia");
        sampleRoom.setRoomIsActive(1);
    }

    @Test
    void testGetActiveRoomsByFunction() {
        when(roomRepository.findByRoomFunctionAndRoomIsActive("pralnia", 1))
                .thenReturn(List.of(sampleRoom));

        List<Room> result = roomService.getActiveRoomsByFunction("pralnia");

        assertEquals(1, result.size());
        assertEquals(sampleRoom, result.get(0));
        verify(roomRepository, times(1)).findByRoomFunctionAndRoomIsActive("pralnia", 1);
    }

    @Test
    void testGetRoomById() {
        when(roomRepository.findByRoomId(1)).thenReturn(sampleRoom);

        Room result = roomService.getRoomById(1);

        assertNotNull(result);
        assertEquals(1, result.getRoomId());
        verify(roomRepository, times(1)).findByRoomId(1);
    }
}

