package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveSaveRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.RoomReserveValidator;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReserveConverter {

    private final RoomService roomService;
    private final RoomReserveValidator roomReserveValidator;
    private final UserService userService;
    private final RoomConverter roomConverter;
    private final RoomReserveService roomReserveService;

    public RoomReserve convert(RoomReserveSaveRequest roomReserveSaveRequest, Long id) {
        Instant startDate = Instant.parse(roomReserveSaveRequest.getStartDate());
        Instant endDate = Instant.parse(roomReserveSaveRequest.getEndDate());
        Room room = roomService.findById(id);
        Integer people = roomReserveSaveRequest.getKids() + roomReserveSaveRequest.getAdults();

        if (!roomReserveValidator.isValidDates(startDate, endDate)) {
            throw new RecordBadRequestException("Incorrect dates");
        }

        Integer days = roomReserveService.calculateDays(startDate, endDate);

        if (!roomReserveValidator.isValidGuestNumber(room.getPeople(), people)) {
            throw new RecordBadRequestException(String.format("This room is for %s people!", room.getPeople()));
        }

        return RoomReserve.builder()
                .room(room)
                .user(userService.findById(roomReserveSaveRequest.getUser()))
                .startDate(Instant.parse(roomReserveSaveRequest.getStartDate()))
                .endDate(Instant.parse(roomReserveSaveRequest.getEndDate()))
                .days(days)
                .kids(roomReserveSaveRequest.getKids())
                .adults(roomReserveSaveRequest.getAdults())
                .roomBedType(roomReserveSaveRequest.getType_bed())
                .roomView(roomReserveSaveRequest.getView())
                .price(room.getPrice() * days)
                .build();
    }

    public RoomReserveResponse convert(RoomReserve roomReserve) {
        String startDate = String.valueOf(roomReserve.getStartDate());
        String endDate = String.valueOf(roomReserve.getEndDate());
        RoomResponse roomResponse = roomConverter.convert(roomReserve.getRoom());
        return RoomReserveResponse.builder()
                .id(roomReserve.getId())
                .startDate(startDate)
                .endDate(endDate)
                .days(roomReserve.getDays())
                .adults(roomReserve.getAdults())
                .kids(roomReserve.getKids())
                .price(roomReserve.getPrice())
                .roomResponse(roomResponse)
                .build();
    }
}
