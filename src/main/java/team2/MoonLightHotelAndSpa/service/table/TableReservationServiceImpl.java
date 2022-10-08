package team2.MoonLightHotelAndSpa.service.table;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.ReservationStatus;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;
    private final TableService tableService;
    private final UserService userService;

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
        Table table = tableService.findById(tableId);
        TableReservation tableReservation = findById(rid);
        if (tableReservation.getTable().getId() != table.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
        return tableReservation;
    }

    @Override
    public Set<TableReservation> findAllByUserId(long userId) {
        User user = userService.findById(userId);
        return tableReservationRepository.findAllByUser(user);
    }

    @Override
    public TableReservation findByUserIdAndTableReservationId(long userId, long tableReservationId) {
        User user = userService.findById(userId);
        TableReservation tableReservation = findById(tableReservationId);
        if (tableReservation.getUser().getId() != user.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }

        return tableReservation;
    }

    @Override
    public Set<TableReservation> findAll() {
        return new HashSet<>(tableReservationRepository.findAll());
    }

    @Override
    @Transactional
    public TableReservation update(TableReservation updatedTableReservation, long id, long rid) {
        tableReservationIdMatch(rid, id);
        TableReservation tableReservation = findById(id);
        tableReservation.setDate(updatedTableReservation.getDate());
        tableReservation.setUpdated(updatedTableReservation.getUpdated());
        tableReservation.setPeople(updatedTableReservation.getPeople());
        tableReservation.setPrice(updatedTableReservation.getPrice());
        tableReservation.setTable(updatedTableReservation.getTable());
        tableReservation.setUser(updatedTableReservation.getUser());

        return tableReservation;
    }

    @Override
    public void tableReservationIdMatch(long tableId, long tableReservationId) {
        TableReservation tableReservation = findById(tableReservationId);
        if(tableReservation.getTable().getId() != tableId) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the table ID.");
        }
    }

    @Override
    public void deleteTableReservationById(long tableReservationId) {
        tableReservationRepository.deleteById(tableReservationId);
    }

    @Override
    public void isPaid(long reservationId) {
        TableReservation tableReservation = findById(reservationId);
        if(tableReservation.getStatus().equals(String.valueOf(ReservationStatus.UNPAID))) {
            throw new RecordBadRequestException("This reservation is unpaid!");
        }
    }
}
