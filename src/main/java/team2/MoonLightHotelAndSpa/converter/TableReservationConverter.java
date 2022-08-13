package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.TableService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class TableReservationConverter {

    private final TableService tableService;
    private final TableConverter tableConverter;
    private final UserConverter userConverter;

    public TableReservation convert(TableReservationRequest tableReservationRequest, long tableId, User user) {
        String combinedDate = tableReservationRequest.getDate() + " " + tableReservationRequest.getHour();
        Instant instantDate = Instant.parse(combinedDate);
        Table table = tableService.findById(tableId);
        return TableReservation.builder()
                .date(instantDate)
//                .updated()
                .people(tableReservationRequest.getPeople())
                .price(tableReservationRequest.getPrice())
                .table(table)
                .user(user)
                .build();
    }

    public TableReservationResponse convert(TableReservation tableReservation) {
        String stringDate = String.valueOf(tableReservation.getDate());
        return TableReservationResponse.builder()
                .id(tableReservation.getId())
                .date(stringDate)
                .people(tableReservation.getPeople())
                .price(tableReservation.getPrice())
//                .updated()
                .tableResponse(tableConverter.convert(tableReservation.getTable()))
                .userResponse(userConverter.convert(tableReservation.getUser()))
                .build();
    }
}
