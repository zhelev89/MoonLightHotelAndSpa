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
import team2.MoonLightHotelAndSpa.service.UserService;

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
        Objects.requireNonNull(tableId);
        Objects.requireNonNull(rid);

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
        TableReservation tableReservation = findById(tableReservationId);
        User user = userService.findById(userId);
        if (tableReservation.getUser().getId() != user.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }

        return tableReservation;
    }

    public Set<TableReservation> findAll() {
        return new HashSet<>(tableReservationRepository.findAll());
    }
}
