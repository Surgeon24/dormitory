package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.Room;
import krakow.university.dormitory.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getActiveRoomsByFunction(String roomFunction) {
        return roomRepository.findByRoomFunctionAndRoomIsActive(roomFunction,1);
    }

    public Room getRoomById(Integer id) {
        return roomRepository.findByRoomId(id);
    }
}
