package team2.MoonLightHotelAndSpa.dataTransferObject.table;

import lombok.Getter;
import team2.MoonLightHotelAndSpa.model.table.TableZone;

@Getter
public class TableUpdateRequest {

    private TableZone zone;
    private int number;
    private int people;
}
