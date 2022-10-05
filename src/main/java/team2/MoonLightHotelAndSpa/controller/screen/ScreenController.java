package team2.MoonLightHotelAndSpa.controller.screen;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.screen.ScreenConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenRequest;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.service.screen.ScreenService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/screens")
public class ScreenController {

    private final ScreenService screenService;
    private final ScreenConverter screenConverter;

    @PostMapping
    public ResponseEntity<Screen> save(@RequestBody ScreenRequest screenRequest) {
        Screen screen = screenConverter.convert(screenRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(screenService.save(screen));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Screen> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(screenService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Screen> update(@PathVariable long id, @RequestBody ScreenRequest screenRequest) {
        return ResponseEntity.ok().body(screenService.update(id, screenRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        screenService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
