package team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;

@Data
@Builder
public class TableReservationResponse {

    private long id;
    private String date;
    private int people;
    private double price;
    private String updated;
    private String status;
    private TableResponse table;
    private UserResponse user;
}
