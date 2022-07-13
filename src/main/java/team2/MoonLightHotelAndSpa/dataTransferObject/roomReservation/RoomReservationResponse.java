package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;

import java.time.Instant;

@Builder
@Data
public class RoomReservationResponse {
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Integer days;
    private Integer adults;
    private Integer kids;
    private Float price;
    private RoomResponse roomResponse;
}
