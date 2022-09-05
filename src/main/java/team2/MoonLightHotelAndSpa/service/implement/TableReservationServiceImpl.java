package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.service.TableReservationService;
import team2.MoonLightHotelAndSpa.service.TableService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;
    private final TableService tableService;

    @Override
    public TableReservation save(TableReservation tableReservation) {
        Objects.requireNonNull(tableReservation);
        return tableReservationRepository.save(tableReservation);
    }

    @Override
    public TableReservation findById(long id) {
        return tableReservationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Table reservation with id:%s, not found", id)));
    }

    @Override
    public List<TableReservation> findAllByTable(long id) {
        Table table = tableService.findById(id);
        return tableReservationRepository.findByTable(table);
    }

    @Override
    public TableReservation findByTableIdAndReservationId(long tableId, long rid) {
        Objects.requireNonNull(tableId);
        Objects.requireNonNull(rid);

        Table table = tableService.findById(tableId);
        TableReservation tableReservation = findById(rid);
        if (tableReservation.getTable().getId() != table.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
        return tableReservation;
    }
}
