package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;

@Data
@Builder
public class RoomReservationResponseV1 {
    private Long id;
    private String start_date;
    private String end_date;
    private Integer days;
    private Integer adults;
    private Integer kids;
    private Float price;
    private RoomResponse room;
}
