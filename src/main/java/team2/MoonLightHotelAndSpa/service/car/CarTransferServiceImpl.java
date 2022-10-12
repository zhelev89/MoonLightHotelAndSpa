package team2.MoonLightHotelAndSpa.service.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.repository.CarTransferRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarTransferServiceImpl implements CarTransferService {

    private CarTransferRepository carTransferRepository;
    private CarService carService;

    @Override
    public CarTransfer save(CarTransfer carTransfer) {
        isCarReserved(carTransfer.getCar().getId(), carTransfer.getDate());
        return carTransferRepository.save(carTransfer);
    }

    @Override
    public List<CarTransfer> findAll() {
        return carTransferRepository.findAll();
    }

    @Override
    @Transactional
    public CarTransfer update(long id, CarTransfer updatedCarTransfer) {
        CarTransfer carTransfer = findById(id);
        carTransfer.setDate(updatedCarTransfer.getDate());
        return carTransfer;
    }

    @Override
    public CarTransfer findById(long id) {
        return carTransferRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Car transfer with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        carTransferRepository.deleteById(id);
    }

    @Override
    public List<CarTransfer> findByCar(long id) {
        Car car = carService.findById(id);
        return carTransferRepository.findByCar(car);
    }

    @Override
    public void carTransferIdMatch(long carId, long carTransferId) {
        CarTransfer carTransfer = findById(carTransferId);
        if (carTransfer.getCar().getId() != carId) {
            throw new RecordBadRequestException("Car transfer ID doesn't match with the car ID.");
        }
    }

    @Override
    public void isPaid(long carTransferId) {
        CarTransfer carTransfer = findById(carTransferId);
        if (carTransfer.getStatus().equals("PAID")) {
            throw new RecordBadRequestException("This car transfer is already paid!");
        }
    }

    @Override
    public void isCarReserved(long carId, Instant date) {
        Car car = carService.findById(carId);
        List<CarTransfer> transfers = carTransferRepository.findByCar(car);
        List<CarTransfer> filtered = transfers.stream().filter(carTransfer -> carTransfer.getDate().equals(date)).toList();
        if(!filtered.isEmpty()) {
            throw new RecordBadRequestException("This car is already reserved for this date");
        }
    }
}