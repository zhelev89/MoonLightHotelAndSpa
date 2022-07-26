package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationSaveRequest {
    private long user;
    private String startDate;
    private String endDate;
    private int adults;
    private int kids;
    private RoomBedType type_bed;
    private RoomView view;
}
