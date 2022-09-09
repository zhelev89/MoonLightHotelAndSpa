package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;

import java.util.List;

public interface TableReservationService {

    TableReservation save(TableReservation tableReservation);

    List<TableReservation> findAllByTable(long id);
}
