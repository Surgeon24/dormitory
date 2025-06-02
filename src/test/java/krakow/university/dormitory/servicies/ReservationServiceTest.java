package krakow.university.dormitory.servicies;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.repositories.ReservationRepository;
import krakow.university.dormitory.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation sampleReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleReservation = new Reservation();
        sampleReservation.setReservationUserId(1);
        sampleReservation.setReservationRoomId(1);
        sampleReservation.setReservationDate(LocalDate.of(2025, 6, 1));
        sampleReservation.setReservationTimeStart(10);
        sampleReservation.setReservationTimeStop(12);
        sampleReservation.setReservationIsActive(1);
    }

    @Test
    void testGetUserReservationsById() {
        when(reservationRepository.findByReservationUserIdAndReservationIsActiveOrderByReservationDateDescReservationTimeStartDesc(1, 1))
                .thenReturn(List.of(sampleReservation));

        List<Reservation> result = reservationService.getUserReservationsById(1);

        assertEquals(1, result.size());
        assertEquals(sampleReservation, result.get(0));
        verify(reservationRepository, times(1))
                .findByReservationUserIdAndReservationIsActiveOrderByReservationDateDescReservationTimeStartDesc(1, 1);
    }

    @Test
    void testGetReservationsByRoomId() {
        when(reservationRepository.findByReservationRoomId(1)).thenReturn(List.of(sampleReservation));

        List<Reservation> result = reservationService.getReservationsByRoomId(1);

        assertEquals(1, result.size());
        assertEquals(sampleReservation, result.get(0));
        verify(reservationRepository, times(1)).findByReservationRoomId(1);
    }

    @Test
    void testCreateReservation() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(sampleReservation);

        Reservation result = reservationService.createReservation(1, 1, sampleReservation.getReservationDate(), 10, 12);

        assertNotNull(result);
        assertEquals(1, result.getReservationUserId());
        assertEquals(1, result.getReservationRoomId());
        assertEquals(10, result.getReservationTimeStart());
        assertEquals(12, result.getReservationTimeStop());
        assertEquals(1, result.getReservationIsActive());

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testCancelReservation() {
        doNothing().when(reservationRepository).deactivateReservationById(1);

        reservationService.cancelReservation(1);

        verify(reservationRepository, times(1)).deactivateReservationById(1);
    }
}
