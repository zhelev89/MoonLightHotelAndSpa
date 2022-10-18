package team2.MoonLightHotelAndSpa.service.screen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.reservation.ReservationStatus;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.ScreenReservationRepository;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class ScreenReservationServiceImpl implements ScreenReservationService {

    private final ScreenReservationRepository screenReservationRepository;
    private final ScreenService screenService;
    private final UserService userService;

    @Override
    public ScreenReservation save(ScreenReservation screenReservation) {
        screenReservation.setPrice(screenReservation.getSeats().length * 10);
        screenReservation.setStatus(ReservationStatus.UNPAID.toString());
        return screenReservationRepository.save(screenReservation);
    }

    public List<ScreenReservation> findByUser(User user) {
        return screenReservationRepository.findByUser(user);
    }

    @Override
    public List<Integer> findFreeSeatsByScreenIdAndDate(long screenId, String date) {
        List<ScreenReservation> byScreenIdAndDate =
                screenReservationRepository.findByScreenIdAndDate(screenId, Instant.parse(date));
        List<Integer> reservedSeats = new ArrayList<>();

        for (ScreenReservation screenReservation : byScreenIdAndDate) {
            @NotNull Integer[] seats = screenReservation.getSeats();
            reservedSeats.addAll(Arrays.stream(seats).toList());
        }

        Integer[] seats = screenService.findById(screenId).getSeats();
        List<Integer> freeSeats = new ArrayList<>(List.of(seats.clone()));
        freeSeats.removeAll(reservedSeats);
        return freeSeats;
    }

    @Override
    public ScreenReservation findByScreenReservationID(long id) {
        return screenReservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("Screen Reservation with id:%s, not found.", id)));
    }

    @Override
    public List<ScreenReservation> findAllByScreenId(long id) {
        Screen screen = screenService.findById(id);
        return screenReservationRepository.findAllByScreen(screen);
    }

    @Override
    public ScreenReservation findByScreenIdAndReservationId(long screenId, long screenReservationId) {
        Screen screen = screenService.findById(screenId);
        ScreenReservation screenReservation = findByScreenReservationID(screenReservationId);
        if (screen.getId() != screenReservation.getScreen().getId()) {
            throw new RecordNotFoundException("Reservation ID doesn't match with the Screen ID.");
        }
        return screenReservation;
    }

    @Override
    public ScreenReservation findByUserIdAndReservationId(long userId, long screenReservationId) {
        User user = userService.findById(userId);
        ScreenReservation screenReservation = findByScreenReservationID(screenReservationId);
        if (user.getId() != screenReservation.getUser().getId()) {
            throw new RecordNotFoundException("Reservation ID doesn't match with the Screen ID.");
        }
        return screenReservation;
    }

    @Override
    public ScreenReservation updateByScreenIdAndReservationId(long screenId, long screenReservationId,
                                                              ScreenReservationUpdateRequest screenReservationUpdateRequest) {
        ScreenReservation reservation = findByScreenIdAndReservationId(screenId, screenReservationId);
        reservation.setDate(Instant.parse(screenReservationUpdateRequest.getDate()));
        reservation.setUser(userService.findById(screenReservationUpdateRequest.getUser()));
        reservation.setSeats(screenReservationUpdateRequest.getSeats());
        reservation.setPrice(screenReservationUpdateRequest.getPrice());
        return reservation;
    }

    @Override
    public void deleteByScreenIdAndReservationsId(long screenId, long reservationId) {
        ScreenReservation reservation = findByScreenIdAndReservationId(screenId, reservationId);
        screenReservationRepository.delete(reservation);
    }

    @Override
    public void isPaid(long screenReservationId) {
        ScreenReservation screenReservation = findByScreenReservationID(screenReservationId);
        if (screenReservation.getStatus().equals("PAID")) {
            throw new RecordBadRequestException("This ScreenReservation is already paid!");
        }
    }
}
