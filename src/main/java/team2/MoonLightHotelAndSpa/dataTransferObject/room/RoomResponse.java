package team2.MoonLightHotelAndSpa.dataTransferObject.room;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
import java.util.Set;

@Builder
@Data
public class RoomResponse {
    private Long id;
    private RoomTitle title;
    private String image;
    private Set<String> images;
    private String description;
    private String facilities;
    private Integer area;
    private RoomView view;
    private Integer people;
    private Float price;
}
