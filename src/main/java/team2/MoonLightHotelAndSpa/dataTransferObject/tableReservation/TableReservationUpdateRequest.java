package team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation;

import lombok.Getter;

@Getter
public class TableReservationUpdateRequest {

    private long user;
    private String date;
    private String hour;
    private long table;
    private int people;
    private double price;
}
