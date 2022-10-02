package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.screen.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.repository.ScreenReservationRepository;
import team2.MoonLightHotelAndSpa.service.ScreenReservationService;
import team2.MoonLightHotelAndSpa.service.ScreenService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class ScreenReservationServiceImpl implements ScreenReservationService {

    private final ScreenReservationRepository screenReservationRepository;
    private final ScreenService screenService;
    private final UserService userService;

    @Override
    public ScreenReservation summarize(Instant date) {
        return screenReservationRepository.findBySeats(date);
    }


    @Override
    public ScreenReservation save(ScreenReservation screenReservation) {
        screenReservation.setPrice(screenReservation.getSeats().length * 10);
        return screenReservationRepository.save(screenReservation);
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
}
