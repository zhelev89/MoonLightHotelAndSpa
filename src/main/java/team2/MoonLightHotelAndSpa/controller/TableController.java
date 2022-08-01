package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.TableConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableSaveRequest;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.service.TableService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/tables")
@Tag(name = "Table")
public class TableController {

    private final TableService tableService;
    private final TableConverter tableConverter;

    @PostMapping
    public ResponseEntity<TableResponse> save(@RequestBody TableSaveRequest tableSaveRequest) {
        Table table = tableConverter.convert(tableSaveRequest);
        Table savedTable = tableService.save(table);
        TableResponse tableResponse = tableConverter.convert(savedTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(tableResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        tableService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
