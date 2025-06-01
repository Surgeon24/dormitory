package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
public class RoomListController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/reserve/{function}")
    public String showRoomsByFunction(@PathVariable("function") String roomFunction, Model model) {
        List<Room> rooms = roomService.getActiveRoomsByFunction(roomFunction);
        Set<String> buildingNames = rooms.stream()
                .map(r -> r.getRoomBuilding().getBuildingName())
                .collect(Collectors.toCollection(TreeSet::new));

        model.addAttribute("rooms", rooms);
        model.addAttribute("roomFunction", roomFunction);
        model.addAttribute("buildingNames", buildingNames);
        return "room-list";
    }
}
