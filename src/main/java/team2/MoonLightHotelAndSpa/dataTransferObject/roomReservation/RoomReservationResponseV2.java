package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

@Builder
@Data
public class RoomReservationResponseV2 {
    private long id;
    private String start_date;
    private String end_date;
    private int days;
    private int adults;
    private int kids;
    private RoomBedType type_bed;
    private RoomView view;
    private String date;
    private float price;
    private RoomResponse room;
    private UserResponse user;
}
