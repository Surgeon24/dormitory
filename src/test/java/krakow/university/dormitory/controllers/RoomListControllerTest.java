package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Building;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.services.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomListController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RoomListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void testShowRoomsByFunction() throws Exception {

        Building building1 = new Building();
        building1.setBuildingName("Building A");

        Building building2 = new Building();
        building2.setBuildingName("Building B");

        Room room1 = new Room();
        room1.setRoomId(1);
        room1.setRoomFunction("Laundry");
        room1.setRoomBuilding(building1);

        Room room2 = new Room();
        room2.setRoomId(2);
        room2.setRoomFunction("Laundry");
        room2.setRoomBuilding(building2);

        List<Room> rooms = Arrays.asList(room1, room2);

        when(roomService.getActiveRoomsByFunction("Laundry")).thenReturn(rooms);

        // Выполнение запроса и проверка результата
        mockMvc.perform(get("/reserve/Laundry"))
                .andExpect(status().isOk())
                .andExpect(view().name("room-list"))
                .andExpect(model().attribute("rooms", rooms))
                .andExpect(model().attribute("roomFunction", "Laundry"))
                .andExpect(model().attributeExists("buildingNames"));
    }
}

