package team2.MoonLightHotelAndSpa.service.room;

import team2.MoonLightHotelAndSpa.model.room.Room;

import java.util.List;

public interface RoomService {
    Room save(Room room);

    List<Room> findAll();

    Room update(long id, Room updatedRoom);

    Room findById(long id);

    void deleteById(long id);
}
