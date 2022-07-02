package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.rooms.Room;

import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> findAll();

    Room update(Long id, Room updatedRoom);

    Room findById(Long id);

    void deleteById(Long id);
}
