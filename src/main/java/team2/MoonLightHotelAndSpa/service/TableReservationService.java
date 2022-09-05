package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;

import java.util.List;
import java.util.Set;

public interface TableReservationService {

    TableReservation save(TableReservation tableReservation);

    TableReservation findById(long id);

    List<TableReservation> findAllByTable(long id);

    Set<TableReservation> findAllByUserId(long userId);

    TableReservation findByTableIdAndReservationId(long tableId, long rid);

    TableReservation findByUserIdAndTableReservationId(long userId, long tableReservationId);

    Set<TableReservation> findAll();

    TableReservation update(TableReservation updatedTableReservation, long id, long rid);

    void tableReservationIdMatch(long tableId, long tableReservationId);
}
