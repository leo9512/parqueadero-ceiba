package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import co.com.ceiba.estacionamiento.neyderdaza.utils.ParkingControlException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles.CAR;
import static co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles.MOTORCYCLE;


@Service
public class ParkingService {
    private ParkingControlRepository parkingControlRepository;
    private static final int MAX_CARS_IN_PARKING = 20;
    private static final int MAX_MOTORCYCLE_IN_PARKING = 10;
    private static final String NOT_MORE_PLACES = "Vehicle cannot enter, there are not more cells available for motorcycles";
    private static final String NOT_ENTER_VEHICLE = "Vehicle cannot enter, license begin for A and today is not available day for it";

    public ParkingService(ParkingControlRepository parkingControlRepository) {
        this.parkingControlRepository = parkingControlRepository;
    }

    public List<ParkingControl> findVehiclesAreParked() {
        List<ParkingControl> vehicles = new ArrayList<>();
        parkingControlRepository.findByIsParking(true).forEach(vehicles::add);
        return vehicles;
    }

    public ParkingControl outsideVehicle (ParkingControl parkingControl){
        parkingControl
                .setVehicleDataOut(CalendarService
                .getActualDate());
        parkingControl
                .setTotalHours(ParkingCalculationService
                .getHoursInParking(parkingControl
                        .getVehicleDataArrived(),parkingControl
                        .getVehicleDataOut()));
        parkingControl
                .setValueToPay(ParkingCalculationService
                        .getValueToPayInParking(parkingControl
                                .getTotalHours(),parkingControl
                                .getVehicleType(),parkingControl
                                .getEngine()));
        parkingControl.setParking(false);
        return parkingControlRepository.save(parkingControl);

    }

    public Optional<ParkingControl> findVehicle(Long id) {
        return parkingControlRepository.findById(id);
    }

    public ParkingControl enterVehicle(ParkingControl parkingControl) {
        validateParkingConditions(parkingControl);
        return parkingControlRepository.save(parkingControl);
    }

    private void validateParkingConditions(ParkingControl parkingControl) {
        canEnterVehicle(parkingControl);
        if(parkingControl.getVehicleType().equalsIgnoreCase(CAR.toString())
                && howManyVehiclesAreParking(CAR.toString()) >= MAX_CARS_IN_PARKING){
            throw new ParkingControlException(NOT_MORE_PLACES);
        }
        if(parkingControl.getVehicleType().equalsIgnoreCase(MOTORCYCLE.toString()) &&
                howManyVehiclesAreParking(MOTORCYCLE.toString())>= MAX_MOTORCYCLE_IN_PARKING){
            throw new ParkingControlException(NOT_MORE_PLACES);
        }
    }

    private int howManyVehiclesAreParking(String vehicleType) {
        List<ParkingControl> vehiclesAreParked = new ArrayList<>();
        parkingControlRepository.findByIsParking(true).forEach(vehiclesAreParked::add);

        List<ParkingControl> vehiclesParkingForType = vehiclesAreParked.stream()
                .filter(line -> line.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
        return vehiclesParkingForType.size();
    }

    private boolean canEnterVehicle(ParkingControl parkingControl) {
        if (licenseBigintWithA(parkingControl) && !isAvailableDay()) {
            throw new ParkingControlException(NOT_ENTER_VEHICLE);
        }
        return true;
    }

    private boolean isAvailableDay() {
        return CalendarService.isMonday() || CalendarService.isSunday() ;
    }

    private boolean licenseBigintWithA(ParkingControl parkingControl) {
        return parkingControl.getLicensePlate().toUpperCase().startsWith("A");
    }
}
