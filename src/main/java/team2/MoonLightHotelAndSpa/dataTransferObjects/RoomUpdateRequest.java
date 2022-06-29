package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.models.rooms.RoomFacilities;
import team2.MoonLightHotelAndSpa.models.rooms.RoomTitle;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class RoomUpdateRequest {

    @NotNull
    private RoomTitle title;

    @NotNull
    private String image;

    @NotNull
    private ArrayList<String> images;

    @NotNull
    private String description;

    @NotNull
    private RoomFacilities facilities;

    @NotNull
    private Integer area;

    @NotNull
    private RoomView view;

    @NotNull
    private Integer people;

    @NotNull
    private Float price;

    @NotNull
    private Integer count;
}
