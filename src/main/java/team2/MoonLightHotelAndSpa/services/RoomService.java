package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.rooms.Room;

import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> findAll();
}
