package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.repository.CarTransferRepository;
import team2.MoonLightHotelAndSpa.service.CarTransferService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarTransferServiceImpl implements CarTransferService {

    private CarTransferRepository carTransferRepository;

    @Override
    public CarTransfer save(CarTransfer carTransfer) {
        return null;
    }

    @Override
    public List<CarTransfer> findAll() {
        return null;
    }

    @Override
    public CarTransfer update(long id, CarTransfer updatedCarTransfer) {
        return null;
    }

    @Override
    public CarTransfer findById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
