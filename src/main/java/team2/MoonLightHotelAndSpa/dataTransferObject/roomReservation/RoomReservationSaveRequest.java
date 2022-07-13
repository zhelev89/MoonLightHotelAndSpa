package team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationSaveRequest {
    private Long userId;
    private Instant startDate;
    private Instant endDate;
    private Integer adults;
    private Integer kids;
    private RoomBedType roomBedType;
    private RoomView view;
    @CreationTimestamp
    private Instant created;
}
