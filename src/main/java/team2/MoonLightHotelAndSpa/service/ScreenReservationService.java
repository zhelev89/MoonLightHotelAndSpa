package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.model.screen.ScreenReservation;

import java.time.Instant;
import java.util.List;

public interface ScreenReservationService {

    ScreenReservation summarize(Instant date);

    ScreenReservation save(ScreenReservation screenReservation);

    ScreenReservation findByScreenReservationID(long id);

    List<ScreenReservation> findAllByScreenId(long id);

    ScreenReservation findByScreenIdAndReservationId(long screenId, long screenReservationId);

    ScreenReservation updateByScreenIdAndReservationId(long screenId, long screenReservationId,
                                                       ScreenReservationUpdateRequest screenReservationUpdateRequest);

    void deleteByScreenIdAndReservationsId(long screenId, long reservationId);
}
