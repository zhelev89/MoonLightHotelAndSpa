package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.repository.CarTransferRepository;
import team2.MoonLightHotelAndSpa.service.CarTransferService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CarTransferServiceImpl implements CarTransferService {

    private CarTransferRepository carTransferRepository;

    @Override
    public CarTransfer save(CarTransfer carTransfer) {
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
        carTransfer.setSeats(updatedCarTransfer.getSeats());
        carTransfer.setUser(updatedCarTransfer.getUser());
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
}
