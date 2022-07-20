package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV1;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV2;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationSaveRequest;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.validator.RoomReservationValidator;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomReservationConverter {

    private final RoomService roomService;
    private final RoomConverter roomConverter;
    private final RoomReservationService roomReservationService;
    private final RoomReservationValidator roomReservationValidator;
    private final UserService userService;
    private final UserConverter userConverter;

    public RoomReservation convert(RoomReservationSaveRequest roomReservationSaveRequest, Long id) {
        Instant startDate = Instant.parse(roomReservationSaveRequest.getStartDate());
        Instant endDate = Instant.parse(roomReservationSaveRequest.getEndDate());
        Room room = roomService.findById(id);
        int people = roomReservationSaveRequest.getKids() + roomReservationSaveRequest.getAdults();
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

    public RoomReservationResponseV1 convert(RoomReservation roomReservation) {
        String startDate = String.valueOf(roomReservation.getStartDate());
        String endDate = String.valueOf(roomReservation.getEndDate());
        RoomResponse roomResponse = roomConverter.convert(roomReservation.getRoom());
        return RoomReservationResponseV1.builder()
                .id(roomReservation.getId())
                .start_date(startDate)
                .end_date(endDate)
                .days(roomReservation.getDays())
                .adults(roomReservation.getAdults())
                .kids(roomReservation.getKids())
                .price(roomReservation.getPrice())
                .room(roomResponse)
                .build();
    }

    public Set<RoomReservationResponseV2> convert(Set<RoomReservation> roomReservationSet) {
        return roomReservationSet.stream()
                .map(roomReserve -> RoomReservationResponseV2.builder()
                        .id(roomReserve.getId())
                        .adults(roomReserve.getAdults())
                        .kids(roomReserve.getKids())
                        .start_date(String.valueOf(roomReserve.getStartDate()))
                        .end_date(String.valueOf(roomReserve.getEndDate()))
                        .days(roomReserve.getDays())
                        .type_bed(roomReserve.getRoomBedType())
                        .view(roomReserve.getRoomView())
                        .price(roomReserve.getPrice())
                        .date(roomReserve.getCreated().toString())
                        .room(roomConverter.convert(roomReserve.getRoom()))
                        .user(userConverter.convert(roomReserve.getUser()))
                        .build()).collect(Collectors.toSet());
    }
}