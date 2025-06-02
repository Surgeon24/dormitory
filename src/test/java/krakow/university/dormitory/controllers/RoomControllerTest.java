package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.*;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.RoomAvailabilityService;
import krakow.university.dormitory.services.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @MockBean
    private RoomAvailabilityService roomAvailabilityService;

    @MockBean
    private ReservationService reservationService;

    @Test
    void showRoomById_roomNotFound_redirectsToError() throws Exception {
        int roomId = 1;
        when(roomService.getRoomById(roomId)).thenReturn(null);

        mockMvc.perform(get("/reserve/room/{id}", roomId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }

    // Пример теста POST makeReservation
    @Test
    void makeReservation_validRequest_redirectsWithSuccess() throws Exception {
        int roomId = 1;
        int userId = 42;

        // Мокируем доступные часы и отсутствие конфликтов
        List<RoomAvailability> availabilities = List.of(
                createRoomAvailability(1, 0, 8*60, 18*60) // availabilityId=1, weekday=0 (Monday), с 8:00 до 18:00
        );
        when(roomAvailabilityService.getRoomAvailabilitiesByRoomId(roomId)).thenReturn(availabilities);
        when(reservationService.getReservationsByRoomId(roomId)).thenReturn(List.of());

        mockMvc.perform(post("/reserve/room/{id}", roomId)
                        .param("userId", String.valueOf(userId))
                        .param("reservationDate", LocalDate.now().with(DayOfWeek.MONDAY).toString())
                        .param("startTime", "09:00")
                        .param("endTime", "10:00")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("success", "Reservation created successfully!"))
                .andExpect(redirectedUrl("/reserve/room/" + roomId));
    }

    private RoomAvailability createRoomAvailability(int availabilityId, int weekday, int startMinutes, int stopMinutes) {
        Availability availability = new Availability();
        availability.setAvailabilityId(availabilityId);
        availability.setAvailabilityWeekday(weekday);
        availability.setAvailabilityStartHour(startMinutes);
        availability.setAvailabilityStopHour(stopMinutes);

        RoomAvailability ra = new RoomAvailability();
        ra.setAvailability(availability);
        return ra;
    }

    // Дополнительные тесты на невалидные случаи, конфликты и т.д. можно добавить по аналогии
}

