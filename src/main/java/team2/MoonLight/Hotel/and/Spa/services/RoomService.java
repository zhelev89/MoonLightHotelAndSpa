package team2.MoonLight.Hotel.and.Spa.services;

import team2.MoonLight.Hotel.and.Spa.models.rooms.Room;

import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> findAll();
}
