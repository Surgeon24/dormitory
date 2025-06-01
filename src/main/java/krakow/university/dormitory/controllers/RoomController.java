package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Reservation;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.entities.RoomAvailability;
import krakow.university.dormitory.services.ReservationService;
import krakow.university.dormitory.services.RoomAvailabilityService;
import krakow.university.dormitory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reserve/room/{id}")
    public String showRoomById(
            @PathVariable("id") Integer roomId,
            @RequestParam(name = "week", defaultValue = "current") String week,
            Model model) {

        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            return "redirect:/error";
        }

        List<RoomAvailability> availabilities = roomAvailabilityService.getRoomAvailabilitiesByRoomId(roomId);
        List<Reservation> allReservations = reservationService.getReservationsByRoomId(roomId);

        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.ISO;

        int currentWeek = today.get(weekFields.weekOfWeekBasedYear());
        int targetWeek = week.equals("next") ? currentWeek + 1 : currentWeek;
        int targetYear = today.getYear();

        List<Reservation> reservations = allReservations.stream()
                .filter(res -> {
                    LocalDate resDate = res.getReservationDate();
                    int resWeek = resDate.get(weekFields.weekOfWeekBasedYear());
                    int resYear = resDate.getYear();
                    return resWeek == targetWeek && resYear == targetYear;
                }).toList();

        Map<Integer, String> weekdayNames = Map.of(
                0, "Monday",
                1, "Tuesday",
                2, "Wednesday",
                3, "Thursday",
                4, "Friday",
                5, "Saturday",
                6, "Sunday"
        );

        LocalDate startOfWeek;
        if ("next".equals(week)) {
            startOfWeek = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        } else {
            startOfWeek = today.with(DayOfWeek.MONDAY);
        }

        List<String> weekDates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i).toString());
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Map<Integer, String> availabilityStartTimes = new HashMap<>();
        Map<Integer, String> availabilityStopTimes = new HashMap<>();
        for (RoomAvailability ra : availabilities) {
            Integer id = ra.getAvailability().getAvailabilityId();
            availabilityStartTimes.put(id, minutesToTime(ra.getAvailability().getAvailabilityStartHour(), timeFormatter));
            availabilityStopTimes.put(id, minutesToTime(ra.getAvailability().getAvailabilityStopHour(), timeFormatter));
        }

        Map<Integer, String> reservationStartTimes = new HashMap<>();
        Map<Integer, String> reservationStopTimes = new HashMap<>();
        for (Reservation res : reservations) {
            Integer id = res.getReservationId();
            reservationStartTimes.put(id, minutesToTime(res.getReservationTimeStart(), timeFormatter));
            reservationStopTimes.put(id, minutesToTime(res.getReservationTimeStop(), timeFormatter));
        }

        List<LocalDate> allowedDates = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate endOfNextWeek = now.with(WeekFields.ISO.getFirstDayOfWeek()).plusWeeks(2).minusDays(1);
        Set<Integer> availableWeekdays = new HashSet<>();
        for (RoomAvailability ra : availabilities) {
            availableWeekdays.add(ra.getAvailability().getAvailabilityWeekday());
        }
        for (LocalDate date = now.plusDays(1); !date.isAfter(endOfNextWeek); date = date.plusDays(1)) {
            int weekdayIndex = (date.getDayOfWeek().getValue() + 6) % 7;
            if (availableWeekdays.contains(weekdayIndex)) {
                allowedDates.add(date);
            }
        }

        Map<String, Set<Integer>> reservedSlots = new HashMap<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

        for (Reservation res : reservations) {
            String dateStr = res.getReservationDate().format(dateFormatter);
            int start = res.getReservationTimeStart();
            int end = res.getReservationTimeStop();

            int roundedStart = (start / 60) * 60;
            int roundedEnd = ((end + 59) / 60) * 60;

            reservedSlots.putIfAbsent(dateStr, new HashSet<>());
            for (int t = roundedStart; t < roundedEnd; t += 60) {
                reservedSlots.get(dateStr).add(t);
            }
        }

        model.addAttribute("reservedSlots", reservedSlots);
        model.addAttribute("dateFormatter", dateFormatter);


        model.addAttribute("reservationForm", new ReservationForm());
        model.addAttribute("room", room);
        model.addAttribute("roomFunction", room.getRoomFunction());
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("reservations", reservations);
        model.addAttribute("weekdayNames", weekdayNames);
        model.addAttribute("availabilityStartTimes", availabilityStartTimes);
        model.addAttribute("availabilityStopTimes", availabilityStopTimes);
        model.addAttribute("reservationStartTimes", reservationStartTimes);
        model.addAttribute("reservationStopTimes", reservationStopTimes);
        model.addAttribute("selectedWeek", week);
        model.addAttribute("allowedDates", allowedDates);
        model.addAttribute("weekDates", weekDates);

        return "room";
    }

    @PostMapping("/reserve/room/{id}")
    public String makeReservation(
            @PathVariable("id") Integer roomId,
            @RequestParam(name = "userId") Integer userId,
            @RequestParam("reservationDate") String dateStr,
            @RequestParam("startTime") String startTimeStr,
            @RequestParam("endTime") String endTimeStr,
            RedirectAttributes redirectAttributes) {

        try {
            LocalDate date = LocalDate.parse(dateStr);
            int startMinutes = timeToMinutes(startTimeStr);
            int endMinutes = timeToMinutes(endTimeStr);

            if (startMinutes >= endMinutes) {
                redirectAttributes.addFlashAttribute("error", "Start time must be before end time.");
                return "redirect:/reserve/room/" + roomId;
            }

            if (endMinutes - startMinutes > 120) {
                redirectAttributes.addFlashAttribute("error", "Reservation cannot be longer than 2 hours.");
                return "redirect:/reserve/room/" + roomId;
            }

            List<RoomAvailability> availabilities = roomAvailabilityService.getRoomAvailabilitiesByRoomId(roomId);
            int dayOfWeek = (date.getDayOfWeek().getValue() + 6) % 7;

            boolean fitsAvailability = availabilities.stream()
                    .filter(ra -> ra.getAvailability().getAvailabilityWeekday() == dayOfWeek)
                    .anyMatch(ra -> startMinutes >= ra.getAvailability().getAvailabilityStartHour()
                            && endMinutes <= ra.getAvailability().getAvailabilityStopHour());

            if (!fitsAvailability) {
                redirectAttributes.addFlashAttribute("error", "Reservation must fit within available hours on that day.");
                return "redirect:/reserve/room/" + roomId;
            }

            List<Reservation> existingReservations = reservationService.getReservationsByRoomId(roomId);
            boolean conflicts = existingReservations.stream()
                    .filter(res -> res.getReservationDate().equals(date))
                    .anyMatch(res -> timesOverlap(startMinutes, endMinutes, res.getReservationTimeStart(), res.getReservationTimeStop()));

            if (conflicts) {
                redirectAttributes.addFlashAttribute("error", "Reservation time conflicts with an existing reservation.");
                return "redirect:/reserve/room/" + roomId;
            }

            reservationService.createReservation(userId, roomId, date, startMinutes, endMinutes);
            redirectAttributes.addFlashAttribute("success", "Reservation created successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid input data: " + e.getMessage());
        }

        return "redirect:/reserve/room/" + roomId;
    }

    private boolean timesOverlap(int start1, int end1, int start2, int end2) {
        return start1 < end2 && end1 > start2;
    }

    private int timeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    private String minutesToTime(Integer minutes, DateTimeFormatter formatter) {
        if (minutes == null) return "";
        return LocalTime.of(minutes / 60, minutes % 60).format(formatter);
    }

    public static class ReservationForm {
        private LocalDate reservationDate;
        private Integer startTime;
        private Integer endTime;

        public LocalDate getReservationDate() { return reservationDate; }
        public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }
        public Integer getStartTime() { return startTime; }
        public void setStartTime(Integer startTime) { this.startTime = startTime; }
        public Integer getEndTime() { return endTime; }
        public void setEndTime(Integer endTime) { this.endTime = endTime; }
    }
}
