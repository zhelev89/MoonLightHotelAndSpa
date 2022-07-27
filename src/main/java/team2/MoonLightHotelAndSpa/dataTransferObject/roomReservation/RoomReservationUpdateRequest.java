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
    private long userId;
    private String start_date;
    private String end_date;
    private int adults;
    private int kids;
    private RoomBedType type_bed;
    private RoomView view;
    private float price;
}
