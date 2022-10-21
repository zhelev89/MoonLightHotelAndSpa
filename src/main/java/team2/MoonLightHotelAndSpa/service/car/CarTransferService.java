package team2.MoonLightHotelAndSpa.service.car;

import team2.MoonLightHotelAndSpa.model.car.CarTransfer;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface CarTransferService {

    CarTransfer save(CarTransfer carTransfer);

    List<CarTransfer> findAll();

    CarTransfer update(long id, CarTransfer updatedCarTransfer);

    CarTransfer findById(long id);

    void deleteById(long id);

    List<CarTransfer> findByCar(long id);

    void carTransferIdMatch(long carId, long carTransferId);

    void isPaid(long carTransferId);

    void isCarReserved(long carId, Instant date);

    List<CarTransfer> findAllByUserId(long id);

    CarTransfer findByUserIdAndTransferId(long uid, long tid);

}
