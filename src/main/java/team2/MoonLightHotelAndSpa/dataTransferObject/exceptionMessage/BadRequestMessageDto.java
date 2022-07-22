package team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestMessageDto {

    private String message;
    private ErrorFieldDto errors;
}
