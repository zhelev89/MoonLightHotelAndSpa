package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.table.TableZone;
import team2.MoonLightHotelAndSpa.repository.TableRepository;
import team2.MoonLightHotelAndSpa.service.TableService;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public Table save(Table table) {
        Objects.nonNull(table);
        try {
            if (table.getZone().equals(TableZone.TERRACE)) {
                table.setSmoking(true);
            }
            return tableRepository.save(table);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException(String.format("Table with this number:%s, already exists.",
                    table.getNumber()));
        }
    }
}
