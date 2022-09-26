package team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage;

import lombok.Data;

import java.util.List;

@Data
public class ErrorFieldDto {

    private List<String> field_name;
}
