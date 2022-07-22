package team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorFieldDto {

    private List<String> field_name;
}
