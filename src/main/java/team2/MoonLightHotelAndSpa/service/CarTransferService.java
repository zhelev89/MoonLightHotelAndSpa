package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.car.CarTransfer;

import java.util.List;

public interface CarTransferService {

    CarTransfer save(CarTransfer carTransfer);

    List<CarTransfer> findAll();

    CarTransfer update(long id, CarTransfer updatedCarTransfer);

    CarTransfer findById(long id);

    void deleteById(long id);
}
