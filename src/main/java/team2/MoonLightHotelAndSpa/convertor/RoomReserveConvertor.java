package team2.MoonLightHotelAndSpa.convertor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveSaveRequest;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReserveConvertor {

    private final RoomService roomService;
    private final UserService userService;
    private final RoomConvertor roomConvertor;

    private final RoomReserveService roomReserveService;

    public RoomReserve convert(RoomReserveSaveRequest roomReserveSaveRequest, Long id) {
        Instant startDate = Instant.parse(roomReserveSaveRequest.getStartDate());
        Instant endDate = Instant.parse(roomReserveSaveRequest.getEndDate());
        Integer days = roomReserveService.calculateDays(startDate, endDate);
        Room room = roomService.findById(id);
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
        RoomResponse roomResponse = roomConvertor.convert(roomReserve.getRoom());
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
