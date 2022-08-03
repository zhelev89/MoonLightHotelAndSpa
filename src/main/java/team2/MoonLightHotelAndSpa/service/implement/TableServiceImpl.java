package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.table.TableZone;
import team2.MoonLightHotelAndSpa.repository.TableRepository;
import team2.MoonLightHotelAndSpa.service.TableService;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public Table save(Table table) {
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

    public Table findById(Long id) {
        return tableRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("Table with this number:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        try {
            tableRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("Table with id:%s, not found.", id));
        }
    }
}
