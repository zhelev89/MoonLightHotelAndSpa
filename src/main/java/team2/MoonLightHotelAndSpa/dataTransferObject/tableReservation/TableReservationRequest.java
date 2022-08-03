package team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableReservationRequest {

    private Long table;
    private String date;
    private String hour;
    private int people;
    private double price;
}
