package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.service.TableReservationService;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;

    @Override
    public TableReservation save(TableReservation tableReservation) {
        Objects.requireNonNull(tableReservation);
        return tableReservationRepository.save(tableReservation);
    }
}
