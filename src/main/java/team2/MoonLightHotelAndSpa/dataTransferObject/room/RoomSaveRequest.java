package team2.MoonLightHotelAndSpa.dataTransferObject.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomSaveRequest {

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
    private Integer people;

    @NotNull
    private Float price;

    @NotNull
    private Integer count;
}
