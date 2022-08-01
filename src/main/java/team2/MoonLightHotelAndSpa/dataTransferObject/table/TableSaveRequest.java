package team2.MoonLightHotelAndSpa.dataTransferObject.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.model.table.TableZone;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableSaveRequest {

    private TableZone zone;
    private int number;
    private int people;
}
