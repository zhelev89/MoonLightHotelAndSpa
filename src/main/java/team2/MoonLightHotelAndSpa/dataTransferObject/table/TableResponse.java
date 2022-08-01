package team2.MoonLightHotelAndSpa.dataTransferObject.table;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.table.TableZone;

@Data
@Builder
public class TableResponse {

    private long id;
    private TableZone zone;
    private int number;
    private int people;
    private boolean smoking;
}
