package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long>
{
    List<RoomAvailability> findByRoomId(Integer roomId);
}
