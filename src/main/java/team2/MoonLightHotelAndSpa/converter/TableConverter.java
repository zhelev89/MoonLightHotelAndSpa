package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableSaveRequest;
import team2.MoonLightHotelAndSpa.model.table.Table;

@Component
@AllArgsConstructor
public class TableConverter {

    public Table convert(TableSaveRequest tableSaveRequest) {
        return Table.builder()
                .zone(tableSaveRequest.getZone())
                .number(tableSaveRequest.getNumber())
                .people(tableSaveRequest.getPeople())
                .build();
    }

    public TableResponse convert(Table table) {
        return TableResponse.builder()
                .id(table.getId())
                .zone(table.getZone())
                .number(table.getNumber())
                .people(table.getPeople())
                .smoking(table.isSmoking())
                .build();
    }
}
