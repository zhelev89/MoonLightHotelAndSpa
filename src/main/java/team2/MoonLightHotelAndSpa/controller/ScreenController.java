package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.ScreenConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenSaveRequest;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.service.ScreenService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/screens")
public class ScreenController {

    private final ScreenService screenService;
    private final ScreenConverter screenConverter;

    @PostMapping
    public ResponseEntity<Screen> save(@RequestBody ScreenSaveRequest screenSaveRequest) {
        Screen screen = screenConverter.convert(screenSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(screenService.save(screen));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Screen> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(screenService.findById(id));
    }
}
