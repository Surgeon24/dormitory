package krakow.university.dormitory.controllers;

import krakow.university.dormitory.dto.RoomDTO;
import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomListController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/by-function/{function}")
    public List<RoomDTO> showRoomsByFunction(@PathVariable("function") String roomFunction) {
        return roomService.getActiveRoomsByFunction(roomFunction)
                .stream()
                .map(RoomDTO::from)
                .toList();
    }
}
