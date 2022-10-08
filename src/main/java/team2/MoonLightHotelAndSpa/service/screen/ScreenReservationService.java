package team2.MoonLightHotelAndSpa.service.screen;

import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.model.screen.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.util.List;

public interface ScreenReservationService {

    List<ScreenReservation> findByUser(User user);

    List<Integer> findFreeSeatsByScreenIdAndDate(long screenId, String date);

    ScreenReservation save(ScreenReservation screenReservation);

    ScreenReservation findByScreenReservationID(long id);

    ScreenReservation findUserIdAndReservationId(long userId, long screenReservationId);

    List<ScreenReservation> findAllByScreenId(long id);

    ScreenReservation findByScreenIdAndReservationId(long screenId, long screenReservationId);

    ScreenReservation updateByScreenIdAndReservationId(long screenId, long screenReservationId,
                                                       ScreenReservationUpdateRequest screenReservationUpdateRequest);

    void deleteByScreenIdAndReservationsId(long screenId, long reservationId);
}