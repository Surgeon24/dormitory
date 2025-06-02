package krakow.university.dormitory.repositories;

import krakow.university.dormitory.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long>
{
    List<Room> findByRoomFunctionAndRoomIsActive(String roomFunction, Integer roomIsActive);
    Room findByRoomId(Integer roomId);
}
