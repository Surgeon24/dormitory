package krakow.university.dormitory.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import krakow.university.dormitory.ThymeleafStubConfig;
import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.User;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

class ReservationsControllerTest {

    private MockMvc mockMvc;

    private UserService userService = mock(UserService.class);
    private ReservationService reservationService = mock(ReservationService.class);

    @BeforeEach
    void setup() {
        ReservationsController controller = new ReservationsController(userService, reservationService);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    @WithMockUser(username = "testuser@example.com", authorities = {"ROLE_USER"})
    void myReservations_ShowsUpcomingReservationsOnly_WhenShowFinishedFalse() throws Exception {
        Principal principal = () -> "testuser@example.com";

        User user = new User();
        user.setUserId(1);
        user.setEmail("testuser@example.com");

        Reservation upcoming = new Reservation();
        upcoming.setReservationDate(LocalDate.now().plusDays(1));
        upcoming.setReservationTimeStart(540); // 9:00
        upcoming.setReservationTimeStop(600);  // 10:00

        Reservation past = new Reservation();
        past.setReservationDate(LocalDate.now().minusDays(1));
        past.setReservationTimeStart(540);
        past.setReservationTimeStop(600);

        when(userService.findByUserEmail("testuser@example.com")).thenReturn(user);
        when(reservationService.getUserReservationsById(1)).thenReturn(List.of(upcoming, past));

        mockMvc.perform(get("/reservations")
                        .param("showFinished", "false")
                        .principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("reservations"))
                .andExpect(model().attribute("showFinished", false));
    }

    @Test
    @WithMockUser(username = "testuser@example.com", authorities = {"ROLE_USER"})
    void myReservations_ShowsAllReservations_WhenShowFinishedTrue() throws Exception {
        Principal principal = () -> "testuser@example.com";

        User user = new User();
        user.setUserId(1);
        user.setEmail("testuser@example.com");

        Reservation upcoming = new Reservation();
        upcoming.setReservationDate(LocalDate.now().plusDays(1));
        upcoming.setReservationTimeStart(540);
        upcoming.setReservationTimeStop(600);

        Reservation past = new Reservation();
        past.setReservationDate(LocalDate.now().minusDays(1));
        past.setReservationTimeStart(540);
        past.setReservationTimeStop(600);

        when(userService.findByUserEmail("testuser@example.com")).thenReturn(user);
        when(reservationService.getUserReservationsById(1)).thenReturn(List.of(upcoming, past));

        mockMvc.perform(get("/reservations")
                        .param("showFinished", "true")
                        .principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("reservations"))
                .andExpect(model().attribute("showFinished", true));
    }

    @Test
    @WithMockUser(username = "testuser@example.com", authorities = {"ROLE_USER"})
    void cancelReservation_RedirectsToReservations() throws Exception {
        mockMvc.perform(post("/cancel-reservation")
                        .param("reservationId", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reservations"));

        verify(reservationService, times(1)).cancelReservation(123);
    }
}



