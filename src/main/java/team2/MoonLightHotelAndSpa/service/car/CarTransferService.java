package team2.MoonLightHotelAndSpa.service.car;

import team2.MoonLightHotelAndSpa.model.car.CarTransfer;

import java.util.List;

public interface CarTransferService {

    CarTransfer save(CarTransfer carTransfer);

    List<CarTransfer> findAll();

    CarTransfer update(long id, CarTransfer updatedCarTransfer);

    CarTransfer findById(long id);

    void deleteById(long id);

    List<CarTransfer> findByCar(long id);

    void carTransferIdMatch(long carId, long carTransferId);
}
