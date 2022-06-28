package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.models.rooms.RoomFacilities;
import team2.MoonLightHotelAndSpa.models.rooms.RoomTitle;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;

import java.util.ArrayList;


@Builder
@Data
public class RoomResponse {

    private Long id;

    private RoomTitle roomTitle;

    private String image;

    private ArrayList<String> images;

    private String description;

    private RoomFacilities roomFacilities;

    private Integer area;

    private RoomView roomView;

    private Integer people;

    private Float price;

}
