package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationSaveRequest;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.validator.RoomReservationValidator;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReservationConverter {

    private final RoomService roomService;
    private final RoomReservationValidator roomReservationValidator;
    private final UserService userService;
    private final RoomConverter roomConverter;
    private final RoomReservationService roomReservationService;

    public RoomReservation convert(RoomReservationSaveRequest roomReservationSaveRequest, Long id) {
        Instant startDate = Instant.parse(roomReservationSaveRequest.getStartDate());
        Instant endDate = Instant.parse(roomReservationSaveRequest.getEndDate());
        Room room = roomService.findById(id);
        Integer people = roomReservationSaveRequest.getKids() + roomReservationSaveRequest.getAdults();
        roomReservationValidator.validDates(startDate, endDate);
        Integer days = roomReservationService.calculateDays(startDate, endDate);
        roomReservationValidator.validGuestNumber(room.getPeople(), people);
        return RoomReservation.builder()
                .room(room)
                .user(userService.findById(roomReservationSaveRequest.getUser()))
                .startDate(Instant.parse(roomReservationSaveRequest.getStartDate()))
                .endDate(Instant.parse(roomReservationSaveRequest.getEndDate()))
                .days(days)
                .kids(roomReservationSaveRequest.getKids())
                .adults(roomReservationSaveRequest.getAdults())
                .roomBedType(roomReservationSaveRequest.getType_bed())
                .roomView(roomReservationSaveRequest.getView())
                .price(room.getPrice() * days)
                .build();
    }

    public RoomReservationResponse convert(RoomReservation roomReservation) {
        String startDate = String.valueOf(roomReservation.getStartDate());
        String endDate = String.valueOf(roomReservation.getEndDate());
        RoomResponse roomResponse = roomConverter.convert(roomReservation.getRoom());
        return RoomReservationResponse.builder()
                .id(roomReservation.getId())
                .startDate(startDate)
                .endDate(endDate)
                .days(roomReservation.getDays())
                .adults(roomReservation.getAdults())
                .kids(roomReservation.getKids())
                .price(roomReservation.getPrice())
                .roomResponse(roomResponse)
                .build();
    }
}
