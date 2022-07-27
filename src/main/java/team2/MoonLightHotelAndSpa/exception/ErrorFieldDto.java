package team2.MoonLightHotelAndSpa.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ErrorFieldDto {

    private List<String> field_name;
}
