package krakow.university.dormitory.controllers;

import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoomListController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/reserve/{function}")
    public String showRoomsByFunction(@PathVariable("function") String roomFunction, Model model) {
        List<Room> rooms = roomService.getActiveRoomsByFunction(roomFunction);
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomFunction", roomFunction);
        return "room-list";
    }
}
