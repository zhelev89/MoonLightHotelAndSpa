package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.room.RoomTitle;

public interface RoomValidator {
    boolean existById(Long id);

    boolean existsByTitle(RoomTitle roomTitle);
}
