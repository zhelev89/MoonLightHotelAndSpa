package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomReservationQuery {
    private String start_date;
    private String end_date;
    private int adults;
    private int kids;
}
