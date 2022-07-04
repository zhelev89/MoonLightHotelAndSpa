package team2.MoonLightHotelAndSpa.dataTransferObject.room;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.room.RoomFacilities;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

import java.util.ArrayList;


@Builder
@Data
public class RoomResponse {

    private Long id;

    private RoomTitle title;

    private String image;

    private ArrayList<String> images;

    private String description;

    private RoomFacilities facilities;

    private Integer area;

    private RoomView view;

    private Integer people;

    private Float price;

}
