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

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

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
        } catch (ConstraintViolationException ex) {
            throw new ConstraintViolationException(ex.getConstraintViolations());
        }
    }

    public Table findById(Long id) {
        return tableRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("Table with this number:%s, not found", id)));
    }

    @Override
    public Table findById(long id) {
        return tableRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Table with id:%s, not found", id)));
    }

    @Override
    @Transactional
    public Table update(Table updatedTable, long id) {
        Table table = findById(id);
        table.setZone(updatedTable.getZone());
        table.setNumber(updatedTable.getNumber());
        table.setPeople(updatedTable.getPeople());

        return table;
    }

    public void deleteById(long id) {
        try {
            tableRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("Table with id:%s, not found.", id));
        }
    }
}
