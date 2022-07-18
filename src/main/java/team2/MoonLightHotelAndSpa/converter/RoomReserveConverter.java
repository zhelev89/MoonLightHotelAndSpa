package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponseV1;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponseV2;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveSaveRequest;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.validator.RoomReserveValidator;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomReserveConverter {

    private final RoomService roomService;
    private final RoomConverter roomConverter;
    private final RoomReserveService roomReserveService;
    private final RoomReserveValidator roomReserveValidator;
    private final UserService userService;
    private final UserConverter userConverter;

    public RoomReserve convert(RoomReserveSaveRequest roomReserveSaveRequest, Long id) {
        Instant startDate = Instant.parse(roomReserveSaveRequest.getStartDate());
        Instant endDate = Instant.parse(roomReserveSaveRequest.getEndDate());
        Room room = roomService.findById(id);
        Integer people = roomReserveSaveRequest.getKids() + roomReserveSaveRequest.getAdults();
        roomReserveValidator.validDates(startDate, endDate);
        Integer days = roomReserveService.calculateDays(startDate, endDate);
        roomReserveValidator.validGuestNumber(room.getPeople(), people);
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

    public RoomReserveResponseV1 convert(RoomReserve roomReserve) {
        String startDate = String.valueOf(roomReserve.getStartDate());
        String endDate = String.valueOf(roomReserve.getEndDate());
        RoomResponse roomResponse = roomConverter.convert(roomReserve.getRoom());
        return RoomReserveResponseV1.builder()
                .id(roomReserve.getId())
                .start_date(startDate)
                .end_date(endDate)
                .days(roomReserve.getDays())
                .adults(roomReserve.getAdults())
                .kids(roomReserve.getKids())
                .price(roomReserve.getPrice())
                .room(roomResponse)
                .build();
    }

    public Set<RoomReserveResponseV2> convert(Set<RoomReserve> roomReserveSet) {
        return roomReserveSet.stream()
                .map(roomReserve -> RoomReserveResponseV2.builder()
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
