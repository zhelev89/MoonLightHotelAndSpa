package team2.MoonLightHotelAndSpa.service.screen;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.repository.ScreenRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    public final ScreenRepository screenRepository;

    @Override
    public Screen save(Screen screen) {
        Objects.requireNonNull(screen);
        try {
            return screenRepository.save(screen);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException(
                    String.format("Screen on this position:%s is already exists.", screen.getPosition()));
        }
    }

    @Override
    public Screen findById(long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("Screen with id:%s, not found.", id)));
    }

    @Override
    public Screen update(long id, ScreenRequest screenRequest) {
        Screen selectedScreen = findById(id);
        selectedScreen.setPosition(screenRequest.getPosition());
        selectedScreen.setTitle(screenRequest.getTitle());
        selectedScreen.setImage(screenRequest.getImage());
        selectedScreen.setSeats(screenRequest.getSeats());
        return selectedScreen;
    }

    @Override
    public void delete(long id) {
        try {
            screenRepository.deleteById(id);
        }  catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("Screen with id:%s, not found.", id));
        }
    }
}
