package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.table.Table;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
}
