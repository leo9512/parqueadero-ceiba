package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    private ParkingControlRepository parkingControlRepository;

    public ParkingService(ParkingControlRepository parkingControlRepository) {
        this.parkingControlRepository = parkingControlRepository;
    }

    public List<ParkingControl> findVehiclesAreParked() {
        List<ParkingControl> vehicles = new ArrayList<>();
        parkingControlRepository.findByIsParking(true).forEach(vehicles::add);
        return vehicles;
    }

    public ParkingControl outsideVehicle (ParkingControl parkingControl){
        parkingControl.setVehicleDataOut(CalendarService.getActualDate());
        parkingControl.setTotalHours(ParkingCalculations.getHoursInParking(parkingControl.getVehicleDataArrived(),parkingControl.getVehicleDataOut()));
        parkingControl.setValueToPay(ParkingCalculations.getValueToPayInParking(parkingControl.getTotalHours(),parkingControl.getVehicleType(),parkingControl.getEngine()));
        parkingControl.setParking(false);
        return parkingControlRepository.save(parkingControl);

    }

    public Optional<ParkingControl> findVehicle(Long id) {
        return parkingControlRepository.findById(id);
    }
}
