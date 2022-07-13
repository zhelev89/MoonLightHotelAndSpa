package team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;

import java.time.Instant;

@Builder
@Data
public class RoomReserveResponse {
    private Long id;
//    private Instant startDate;
//    private Instant endDate;
    private Integer days;
    private Integer adults;
    private Integer kids;
    private Float price;
    private RoomResponse roomResponse;
}
