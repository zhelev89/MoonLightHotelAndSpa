package team2.MoonLightHotelAndSpa.convertor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationSaveRequest;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Duration;
import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReservationConvertor {

    private static UserService userService;
    private static RoomConvertor roomConvertor;

    public static RoomReservation convert(RoomReservationSaveRequest roomReservationSaveRequest) {
        Instant instant = Instant.now();
        User user = userService.findById(roomReservationSaveRequest.getUserId());
        Long days = Duration.between(roomReservationSaveRequest.getStartDate(), roomReservationSaveRequest.getEndDate()).toDays();
        Integer daysInt = days.intValue();

        return RoomReservation.builder()
                .user(user)
                .startDate(roomReservationSaveRequest.getStartDate())
                .endDate(roomReservationSaveRequest.getEndDate())
                .days(daysInt)
                .adults(roomReservationSaveRequest.getAdults())
                .kids(roomReservationSaveRequest.getKids())
                .roomBedType(roomReservationSaveRequest.getRoomBedType())
                .view(roomReservationSaveRequest.getView())
                .price(100F)
                .created(instant)
                .status("adsf")
                .room(Room.builder().build())
                .build();
    }

    public static RoomReservationResponse convert(RoomReservation roomReservation) {
        RoomResponse roomResponse = roomConvertor.convert(roomReservation.getRoom());
        return RoomReservationResponse.builder()
                .id(roomReservation.getId())
                .startDate(roomReservation.getStartDate())
                .endDate(roomReservation.getEndDate())
                .days(roomReservation.getDays())
                .adults(roomReservation.getAdults())
                .kids(roomReservation.getKids())
                .price(roomReservation.getPrice())
                .roomResponse(roomResponse)
                .build();
    }
}
