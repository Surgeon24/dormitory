package krakow.university.dormitory.services;

import krakow.university.dormitory.dto.AvailabilityDTO;
import krakow.university.dormitory.dto.ReservationDTO;
import krakow.university.dormitory.dto.RoomDTO;
import krakow.university.dormitory.dto.RoomDetailsDTO;
import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.entities.RoomAvailability;
import krakow.university.dormitory.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    public List<Room> getActiveRoomsByFunction(String roomFunction) {
        return roomRepository.findByRoomFunctionAndRoomIsActive(roomFunction,1);
    }

    public Room getRoomById(Integer id) {
        return roomRepository.findByRoomId(id);
    }

    public RoomDetailsDTO buildRoomDetailsDTO(Room room, String week) {
        RoomDetailsDTO dto = new RoomDetailsDTO();

        dto.setRoom(mapToRoomDTO(room));
        dto.setRoomFunction(room.getRoomFunction());

        LocalDate startOfWeek = getStartOfWeek(week);
        List<LocalDate> allowedDates = IntStream.range(0, 7)
                .mapToObj(startOfWeek::plusDays)
                .collect(Collectors.toList());

        dto.setAllowedDates(allowedDates);
        dto.setSelectedWeek(week);

        List<AvailabilityDTO> availabilities = roomAvailabilityService.getAvailabilityByRoomId(room.getRoomId())
                .stream()
                .map(this::mapToAvailabilityDTO)
                .collect(Collectors.toList());

        List<ReservationDTO> reservations = reservationService.getReservationsByRoomId(room.getRoomId())
                .stream()
                .filter(r -> allowedDates.contains(r.getReservationDate()))
                .map(this::mapToReservationDTO)
                .collect(Collectors.toList());

        dto.setAvailabilities(availabilities);
        dto.setReservations(reservations);

        dto.setWeekdayNames(getWeekdayNames());
        dto.setAvailabilityStartTimes(getTimeRangeMap(availabilities, true));
        dto.setAvailabilityStopTimes(getTimeRangeMap(availabilities, false));
        dto.setReservationStartTimes(getTimeRangeMap(reservations, true));
        dto.setReservationStopTimes(getTimeRangeMap(reservations, false));

        return dto;
    }

    private RoomDTO mapToRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getRoomId());
        dto.setName(room.getRoomName());
        dto.setFunction(room.getRoomFunction());
        dto.setMaxCapacity(room.getRoomMaxCapacity());
        dto.setIsActive(room.getRoomIsActive() != null && room.getRoomIsActive() == 1);
        dto.setBuildingId(room.getRoomBuilding().getBuildingId());
        return dto;
    }

    private AvailabilityDTO mapToAvailabilityDTO(RoomAvailability a) {
        AvailabilityDTO dto = new AvailabilityDTO();
        dto.setDayOfWeek(a.getAvailabilityDay());
        dto.setStartTime(a.getAvailabilityStartTime());
        dto.setStopTime(a.getAvailabilityStopTime());
        return dto;
    }

    private ReservationDTO mapToReservationDTO(Reservation r) {
        ReservationDTO dto = new ReservationDTO();
        dto.setUserId(r.getReservationUserId());
        dto.setDate(r.getReservationDate());
        dto.setStartTime(r.getReservationTimeStart());
        dto.setStopTime(r.getReservationTimeStop());
        return dto;
    }

    private Map<Integer, String> getWeekdayNames() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Monday");
        map.put(2, "Tuesday");
        map.put(3, "Wednesday");
        map.put(4, "Thursday");
        map.put(5, "Friday");
        map.put(6, "Saturday");
        map.put(7, "Sunday");
        return map;
    }

    private Map<Integer, String> getTimeRangeMap(List<?> list, boolean isStart) {
        Map<Integer, String> map = new HashMap<>();
        for (Object item : list) {
            int key;
            int time;
            if (item instanceof AvailabilityDTO a) {
                key = a.getDayOfWeek();
                time = isStart ? a.getStartTime() : a.getStopTime();
            } else if (item instanceof ReservationDTO r) {
                key = r.getDate().getDayOfWeek().getValue();
                time = isStart ? r.getStartTime() : r.getStopTime();
            } else continue;

            map.put(key, formatTime(time));
        }
        return map;
    }

    private String formatTime(int time) {
        String raw = String.format("%04d", time);
        return raw.substring(0, 2) + ":" + raw.substring(2);
    }

    private LocalDate getStartOfWeek(String week) {
        LocalDate now = LocalDate.now();
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        return week.equals("next") ? monday.plusWeeks(1) : monday;
    }
}

