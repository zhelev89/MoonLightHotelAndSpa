package team2.MoonLightHotelAndSpa.service.room;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {

    private final UserService userService;
    private final RoomReservationRepository roomReservationRepository;

    public RoomReservation save(RoomReservation roomReservation) {
        Objects.requireNonNull(roomReservation);
        return roomReservationRepository.save(roomReservation);
    }

    public RoomReservation findByUserIdAndReservationId(long uid, long rid) {
        User foundUser = userService.findById(uid);
        RoomReservation foundReservation = findById(rid);
        if (foundReservation.getUser().getId() != foundUser.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
        return foundReservation;
    }

    public Set<RoomReservation> findAllByUserId(long id) {
        User userById = userService.findById(id);
        return roomReservationRepository.findAllByUser(userById);
    }

    public Set<RoomReservation> findAll() {
        return new HashSet<>(roomReservationRepository.findAll());
    }

    @Override
    public int calculateDays(Instant startDate, Instant endDate) {

        long daysLong = Duration.between(startDate, endDate).toDays();
        if (daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return (int) daysLong;
    }

    @Override
    public RoomReservation findById(long id) {
        return roomReservationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Room reservation with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public void roomReservationIdMatch(long roomId, long roomReservationId) {
        RoomReservation roomReservation = findById(roomReservationId);
        if (roomReservation.getRoom().getId() != roomId) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the room ID.");
        }
    }

    @Override
    @Transactional
    public RoomReservation update(long id, long rid, RoomReservation updatedRoomReservation) {
        RoomReservation roomReservation = findById(rid);
        roomReservation.setStartDate(updatedRoomReservation.getStartDate());
        roomReservation.setEndDate(updatedRoomReservation.getEndDate());
        roomReservation.setAdults(updatedRoomReservation.getAdults());
        roomReservation.setKids(updatedRoomReservation.getKids());
        roomReservation.setRoomBedType(updatedRoomReservation.getRoomBedType());
        roomReservation.setRoomView(updatedRoomReservation.getRoomView());
        roomReservation.setPrice(updatedRoomReservation.getPrice());
        return roomReservation;
    }

    @Override
    public List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people) {
        return roomReservationRepository.findAllAvailableRooms(start_date, end_date ,people);
    }

    @Override
    public ResponseEntity<?> findAllAvailableRoomsDetailed(String start_date, String end_date, int adults, int kids, RoomView roomView, RoomTitle roomTitle) {
        Instant startDateInst = Instant.parse(start_date);
        Instant endDateInst = Instant.parse(end_date);
        int people = adults + kids;
        List<Room> rooms = findAllAvailableRooms(startDateInst, endDateInst, people);
        if(rooms.isEmpty()) {
            String error = "No available rooms on these dates";
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        List<Room> roomsByViewAndType = rooms.stream().filter(room -> room.getView().equals(roomView)).filter(room -> room.getTitle().equals(roomTitle)).toList();
        if (roomsByViewAndType.isEmpty()) {
            List<String> errorMessages = new ArrayList<>();
            List<Room> sortedFromView = rooms.stream().filter(room -> room.getView().equals(roomView)).toList();
            if (sortedFromView.size() == 0) {
                errorMessages.add("Room with this room view");
            }
            List<Room> sortedFromTitle = rooms.stream().filter(room -> room.getTitle().equals(roomTitle)).toList();
            if (sortedFromTitle.size() == 0) {
                errorMessages.add("Room with this room title");
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.OK);
        }
        return new ResponseEntity<>(roomsByViewAndType, HttpStatus.OK);
    }

    @Override
    public void isPaid(long reservationId) {
        RoomReservation roomReservation = findById(reservationId);
        if(roomReservation.getStatus().equals("PAID")) {
            throw new RecordBadRequestException("This reservation is already paid!");
        }
    }
}
