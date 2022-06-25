package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.models.rooms.RoomBedType;
import team2.MoonLightHotelAndSpa.models.rooms.RoomType;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;

import java.util.ArrayList;


@Builder
@Data
public class RoomResponse {

    private Long id;

    private RoomType title;

    private String image;

    private ArrayList<String> images;

    private String description;

    private RoomBedType facilities;

    private RoomView area;

    private Integer people;

    private Float price;

}
