package team2.MoonLight.Hotel.and.Spa.dataTransferObjects;

import lombok.Data;
import team2.MoonLight.Hotel.and.Spa.models.rooms.RoomType;
import team2.MoonLight.Hotel.and.Spa.models.rooms.RoomView;

@Data
public class RoomSaveRequest {

    private RoomType roomType;
    private RoomView roomView;
    private Double roomPrice;
}
