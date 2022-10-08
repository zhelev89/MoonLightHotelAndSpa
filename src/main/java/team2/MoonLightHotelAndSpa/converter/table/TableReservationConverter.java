package team2.MoonLightHotelAndSpa.converter.table;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.converter.user.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.model.reservation.ReservationStatus;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.table.TableService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class TableReservationConverter {

    private final TableService tableService;
    private final TableConverter tableConverter;
    private final UserConverter userConverter;
    private final UserService userService;

    public TableReservation convert(TableReservationRequest tableReservationRequest, long tableId, User user) {
        String status = String.valueOf(ReservationStatus.UNPAID);
        Table table = tableService.findById(tableId);
        return TableReservation.builder()
                .date(convertRequestDateAndHourToInstant(tableReservationRequest.getDate(), tableReservationRequest.getHour()))
                .updated(Instant.now())
                .people(tableReservationRequest.getPeople())
                .price(tableReservationRequest.getPrice())
                .table(table)
                .user(user)
                .status(status)
                .build();
    }

    public TableReservationResponse convert(TableReservation tableReservation) {
        String stringDate = String.valueOf(tableReservation.getDate());
        return TableReservationResponse.builder()
                .id(tableReservation.getId())
                .date(stringDate)
                .people(tableReservation.getPeople())
                .price(tableReservation.getPrice())
                .updated(tableReservation.getUpdated().toString())
                .status(tableReservation.getStatus())
                .table(tableConverter.convert(tableReservation.getTable()))
                .user(userConverter.convert(tableReservation.getUser()))
                .build();
    }

    public TableReservation convert(TableReservationUpdateRequest tableReservationUpdateRequest) {
        Instant date = convertRequestDateAndHourToInstant(tableReservationUpdateRequest.getDate(), tableReservationUpdateRequest.getHour());
        Table table = tableService.findById(tableReservationUpdateRequest.getTable());
        User user = userService.findById(tableReservationUpdateRequest.getUser());
        return TableReservation.builder()
                .date(date)
                .updated(Instant.now())
                .people(tableReservationUpdateRequest.getPeople())
                .price(tableReservationUpdateRequest.getPrice())
                .table(table)
                .user(user)
                .build();
    }

    public TableReservationResponse convertSummarize(TableReservationRequest tableReservationRequest, long tableId, User user) {
        Table table = tableService.findById(tableId);
        String date = tableReservationRequest.getHour() + " " + tableReservationRequest.getDate();
        return TableReservationResponse.builder()
                .date(date)
                .people(tableReservationRequest.getPeople())
                .price(tableReservationRequest.getPrice())
                .updated("")
                .table(tableConverter.convert(table))
                .user(userConverter.convert(user))
                .build();
    }

    private Instant convertRequestDateAndHourToInstant(String date, String hour) {
        String concatenatedDate = date + " " + hour;

        return LocalDateTime
                .parse(concatenatedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
