package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.repository.ScreenReservationRepository;
import team2.MoonLightHotelAndSpa.service.ScreenReservationService;

@Service
@AllArgsConstructor
public class ScreenReservationServiceImpl implements ScreenReservationService {

    private final ScreenReservationRepository screenReservationRepository;

    @Override
    public ScreenReservation save(ScreenReservation screenReservation) {
        return screenReservationRepository.save(screenReservation);
    }
}
