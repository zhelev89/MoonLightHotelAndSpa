package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;

@Data
@Builder
public class RoomReservationResponseV1 {
    private long id;
    private String start_date;
    private String end_date;
    private int days;
    private int adults;
    private int kids;
    private float price;
    private RoomResponse room;
    private String status;
}
