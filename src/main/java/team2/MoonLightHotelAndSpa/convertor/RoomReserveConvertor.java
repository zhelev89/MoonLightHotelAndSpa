package team2.MoonLightHotelAndSpa.convertor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveSaveRequest;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.RoomService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReserveConvertor {

    private final RoomService roomService;
    private final UserService userService;
    private final RoomConvertor roomConvertor;

    public RoomReserve convert(RoomReserveSaveRequest roomReserveSaveRequest, Long id) {
//        Instant instant = Instant.now();
        Room room = roomService.findById(id);
        return RoomReserve.builder()
                .room(room)
                .user(userService.findById(roomReserveSaveRequest.getUser()))
//                .startDate(Instant.parse(roomReserveSaveRequest.getStartDate()))
//                .endDate(Instant.parse(roomReserveSaveRequest.getEndDate()))
                .kids(roomReserveSaveRequest.getKids())
                .adults(roomReserveSaveRequest.getAdults())
                .roomBedType(roomReserveSaveRequest.getType_bed())
                .roomView(roomReserveSaveRequest.getView())
                .build();

//        Long days = Duration.between(roomReservationSaveRequest.getStartDate(), roomReservationSaveRequest.getEndDate()).toDays();
//        Integer daysInt = days.intValue();
//        return RoomReserve.builder()
//                .user(user)
//                .startDate(roomReservationSaveRequest.getStartDate())
//                .endDate(roomReservationSaveRequest.getEndDate())
//                .days(daysInt)
//                .adults(roomReservationSaveRequest.getAdults())
//                .kids(roomReservationSaveRequest.getKids())
//                .roomBedType(roomReservationSaveRequest.getRoomBedType())
//                .view(roomReservationSaveRequest.getView())
//                .price(100F)
//                .created(instant)
//                .status("adsf")
//                .room(Room.builder().build())
//                .build();
//    }

//    public static RoomReserveResponse convert(RoomReserve roomReserve) {
//        RoomResponse roomResponse = roomConvertor.convert(roomReserve.getRoom());
//        return RoomReserveResponse.builder()
//                .id(roomReserve.getId())
//                .startDate(roomReserve.getStartDate())
//                .endDate(roomReserve.getEndDate())
//                .days(roomReserve.getDays())
//                .adults(roomReserve.getAdults())
//                .kids(roomReserve.getKids())
//                .price(roomReserve.getPrice())
//                .roomResponse(roomResponse)
//                .build();
//    }
    }
}
