package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.table.Table;

public interface TableService {

    Table save(Table table);

    Table findById(long id);

    Table update(Table updatedTable, long id);

    void deleteById(long id);
}