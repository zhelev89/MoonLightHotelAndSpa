package team2.MoonLightHotelAndSpa.controller.contactUs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.converter.contactUs.ContactUsConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.contactUsForm.ContactUsRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.model.contactUsForm.ContactUs;
import team2.MoonLightHotelAndSpa.service.contactUs.ContactUsService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/contacts")
@Tag(name = "ContactUs")
public class ContactUsController {

    private final ContactUsService contactUsService;
    private final ContactUsConverter converter;

    @PostMapping
    @Operation(summary = "Contact us", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> contactUs(@RequestBody ContactUsRequest contactUsRequest) {
        ContactUs contactUs = converter.convert(contactUsRequest);
        contactUsService.saveContactUs(contactUs);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
