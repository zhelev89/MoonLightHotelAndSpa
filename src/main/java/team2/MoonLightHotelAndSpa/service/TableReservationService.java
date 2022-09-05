package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;

import java.util.List;

public interface TableReservationService {

    TableReservation save(TableReservation tableReservation);

    TableReservation findById(long id);

    List<TableReservation> findAllByTable(long id);

    TableReservation findByTableIdAndReservationId(long tableId, long rid);
}
