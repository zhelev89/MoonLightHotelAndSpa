package team2.MoonLightHotelAndSpa.dataTransferObject.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

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
    private String[] images;

    @NotNull
    private String description;

    @NotNull
    private Integer area;

    @NotNull
    private RoomView view;

    @NotNull
    private Integer adults;

    @NotNull
    private Integer kids;

    @NotNull
    private Float price;

    @NotNull
    private Integer count;
}
