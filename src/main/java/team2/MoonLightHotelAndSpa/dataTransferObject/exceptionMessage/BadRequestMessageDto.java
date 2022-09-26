package team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage;

import lombok.Data;

@Data
public class BadRequestMessageDto {

    private String message;
    private ErrorFieldDto errors;
}
