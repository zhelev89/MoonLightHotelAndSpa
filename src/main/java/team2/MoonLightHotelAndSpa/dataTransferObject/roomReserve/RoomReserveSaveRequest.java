package team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve;

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
public class RoomReserveSaveRequest {
    private Long user;
//    private String startDate;
//    private String endDate;
    private Integer adults;
    private Integer kids;
    private RoomBedType type_bed;
    private RoomView view;
}
