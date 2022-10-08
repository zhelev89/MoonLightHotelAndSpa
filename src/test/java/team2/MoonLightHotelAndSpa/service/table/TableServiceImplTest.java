package team2.MoonLightHotelAndSpa.service.table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.table.TableZone;
import team2.MoonLightHotelAndSpa.repository.TableRepository;
import team2.MoonLightHotelAndSpa.service.table.TableServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TableServiceImplTest {

    @Mock
    private TableRepository tableRepository;

    private TableServiceImpl tableService;

    @BeforeEach
    public void setUp() {
        tableService = new TableServiceImpl(tableRepository);
    }

    @Test
    public void verifySave() {
        Table table = Table.builder()
                .zone(TableZone.BAR)
                .people(3)
                .number(1)
                .build();

        tableService.save(table);
        Mockito.verify(tableRepository, Mockito.times(1)).save(table);
    }

    @Test
    public void verifyFindById() {
        long id = 1;
        Mockito.when(tableRepository.findById(id))
                .thenReturn(Optional.of(
                        Table.builder()
                                .id(id)
                                .build()));

        Table table = tableService.findById(id);
        Mockito.verify(tableRepository, Mockito.times(1)).findById(id);
        Assertions.assertEquals(id, table.getId());
    }

    @Test
    public void verifyFindByIdThrowsException() {
        long id = 1;
        String message = String.format("Table with id:%s, not found", id);

        RecordNotFoundException exception = Assertions.assertThrows(
                RecordNotFoundException.class, () -> tableService.findById(id));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyUpdate() {
        Table table = Table.builder()
                .id(1)
                .zone(TableZone.BAR)
                .number(1)
                .people(3)
                .build();

        Mockito.when(tableRepository.findById(table.getId())).thenReturn(Optional.of(
                Table.builder()
                        .id(table.getId())
                        .zone(table.getZone())
                        .number(table.getNumber())
                        .people(table.getPeople())
                        .build()));

        Table update = Table.builder()
                .zone(TableZone.TERRACE)
                .people(5)
                .number(2)
                .build();

        Table updatedTable = tableService.update(update, table.getId());

        Assertions.assertEquals(updatedTable.getId(), table.getId());
        Assertions.assertEquals(update.getPeople(), updatedTable.getPeople());
        Assertions.assertEquals(update.getNumber(), updatedTable.getNumber());
        Assertions.assertEquals(update.getZone(), updatedTable.getZone());
    }

    @Test
    public void verifyDeleteById() {
        long id = 1;

        tableService.deleteById(id);
        Mockito.verify(tableRepository, Mockito.times(1)).deleteById(id);
    }
}
