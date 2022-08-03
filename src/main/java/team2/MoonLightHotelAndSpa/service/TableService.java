package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.table.Table;

public interface TableService {

    Table save(Table table);

    Table findById(Long id);

    void deleteById(long id);
}
