package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.converter.ContactUsConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.contactUsForm.ContactUsRequest;
import team2.MoonLightHotelAndSpa.model.contactUsForm.ContactUs;
import team2.MoonLightHotelAndSpa.service.ContactUsService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/contacts")
@Tag(name = "ContactUs")
public class ContactUsController {

    private final ContactUsService contactUsService;
    private final ContactUsConverter converter;

    @PostMapping
    public ResponseEntity<HttpStatus> contactUs(@RequestBody ContactUsRequest contactUsRequest) {
        ContactUs contactUs = converter.convert(contactUsRequest);
        contactUsService.saveContactUs(contactUs);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
