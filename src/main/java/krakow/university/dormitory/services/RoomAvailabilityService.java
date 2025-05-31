package krakow.university.dormitory.services;

import krakow.university.dormitory.entities.RoomAvailability;
import krakow.university.dormitory.repositories.RoomAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAvailabilityService {
    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public List<RoomAvailability> getRoomAvailabilitiesByRoomId(Integer roomId) {
        return roomAvailabilityRepository.findByRoomId(roomId);
    }
}
