package team2.MoonLightHotelAndSpa.exception;

import lombok.Data;
import team2.MoonLightHotelAndSpa.exception.ErrorFieldDto;

@Data
public class BadRequestMessageDto {

    private String message;
    private ErrorFieldDto errors;
}
