package team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage;

import lombok.Getter;

@Getter
public class BadRequestMessageDto {

    private String message;
    private ErrorFieldDto errors;
}
