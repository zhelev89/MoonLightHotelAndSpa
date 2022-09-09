package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.service.TableReservationService;
import team2.MoonLightHotelAndSpa.service.TableService;

import java.util.List;
import java.util.Objects;

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
    public List<TableReservation> findAllByTable(long id) {
        Table table = tableService.findById(id);
        return tableReservationRepository.findByTable(table);
    }
}
