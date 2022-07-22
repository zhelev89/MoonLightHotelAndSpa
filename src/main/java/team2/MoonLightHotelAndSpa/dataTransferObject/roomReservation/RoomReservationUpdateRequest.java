package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

@Data
@Builder
@AllArgsConstructor
public class RoomReservationUpdateRequest {
    private Long userId;
    private String start_date;
    private String end_date;
    private Integer adults;
    private Integer kids;
    private RoomBedType type_bed;
    private RoomView view;
    private Float price;
}
