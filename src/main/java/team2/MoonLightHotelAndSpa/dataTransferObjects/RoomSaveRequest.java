package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.models.rooms.RoomFacilities;
import team2.MoonLightHotelAndSpa.models.rooms.RoomTitle;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomSaveRequest {

    @NotNull
    private RoomTitle roomTitle;

    @NotNull
    private String image;

    @NotNull
    private ArrayList<String> images;

    @NotNull
    private String description;

    @NotNull
    private RoomFacilities roomFacilities;

    @NotNull
    private Integer area;

    @NotNull
    private RoomView roomView;

    @NotNull
    private Integer people;

    @NotNull
    private Float price;

    @NotNull
    private Integer count;
}
