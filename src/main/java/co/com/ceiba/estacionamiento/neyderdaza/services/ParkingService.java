package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.dto.ParkingControlDTO;
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
    private CalendarService calendarService;
    private ParkingCalculationService parkingCalculationService;
    private static final int MAX_CARS_IN_PARKING = 20;
    private static final int MAX_MOTORCYCLE_IN_PARKING = 10;
    private static final String NOT_MORE_PLACES_FOR_CARS = "Vehicle cannot enter, there are not more cells available for cars";
    private static final String NOT_MORE_PLACES_FOR_MOTORCYCLES = "Vehicle cannot enter, there are not more cells available for motorcycles";
    private static final String NOT_ENTER_VEHICLE = "Vehicle cannot enter, license begin for A and today is not available day for it";

    public ParkingService(ParkingControlRepository parkingControlRepository, CalendarService calendarService, ParkingCalculationService parkingCalculationService) {
        this.parkingControlRepository = parkingControlRepository;
        this.calendarService = calendarService;
        this.parkingCalculationService = parkingCalculationService;
    }

    public List<ParkingControl> findVehiclesAreParked() {
        List<ParkingControl> vehicles = new ArrayList<>();
        parkingControlRepository.findByIsParking(true).forEach(vehicles::add);
        return vehicles;
    }

    public ParkingControl outsideVehicle (ParkingControl parkingControl){
        parkingControl
                .setVehicleDataOut(calendarService
                .getActualDate());
        parkingControl
                .setTotalHours(parkingCalculationService
                .getHoursInParking(
                        calendarService.stringToDate(parkingControl.getVehicleDataArrived()),
                        calendarService.stringToDate(parkingControl.getVehicleDataOut())));
        parkingControl
                .setValueToPay(parkingCalculationService
                        .getValueToPayInParking(parkingControl
                                .getTotalHours(),parkingControl
                                .getVehicleType(),parkingControl
                                .getEngine()));
        parkingControl.setParking(false);
        return parkingControlRepository.save(parkingControl);

    }

    public ParkingControl findVehicle(Long id) {
        ParkingControl parkingControl1 = new ParkingControl();
        Optional<ParkingControl> parkingConsult = parkingControlRepository.findById(id);
        if(parkingConsult.isPresent()){
            parkingControl1= parkingConsult.get();
        }
        return parkingControl1;
    }

    public ParkingControl enterVehicle(ParkingControlDTO dto) {
        ParkingControl parkingControl = new ParkingControl();
        parkingControl.setEngine(dto.getEngine());
        parkingControl.setParking(true);
        parkingControl.setVehicleType(dto.getVehicleType());
        parkingControl.setLicensePlate(dto.getLicensePlate());
        validateParkingConditions(parkingControl);
        parkingControl.setVehicleDataArrived(calendarService.getActualDate());
        return parkingControlRepository.save(parkingControl);
    }

    public boolean validateParkingConditions(ParkingControl parkingControl) {
        canEnterVehicle(parkingControl);
        if(parkingControl.getVehicleType().equalsIgnoreCase(CAR.toString())
                && howManyVehiclesAreParking(CAR.toString()) >= MAX_CARS_IN_PARKING){
            throw new ParkingControlException(NOT_MORE_PLACES_FOR_CARS);
        }
        if(parkingControl.getVehicleType().equalsIgnoreCase(MOTORCYCLE.toString()) &&
                howManyVehiclesAreParking(MOTORCYCLE.toString())>= MAX_MOTORCYCLE_IN_PARKING){
            throw new ParkingControlException(NOT_MORE_PLACES_FOR_MOTORCYCLES);
        }
        return true;
    }

    public int howManyVehiclesAreParking(String vehicleType) {
        List<ParkingControl> vehiclesAreParked = new ArrayList<>();
        parkingControlRepository.findByIsParking(true).forEach(vehiclesAreParked::add);

        List<ParkingControl> vehiclesParkingForType = vehiclesAreParked.stream()
                .filter(line -> line.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
        return vehiclesParkingForType.size();
    }

    public boolean canEnterVehicle(ParkingControl parkingControl) {
        if (licenseBigintWithA(parkingControl) && !isAvailableDay()) {
            throw new ParkingControlException(NOT_ENTER_VEHICLE);
        }
        return true;
    }

    public boolean licenseBigintWithA(ParkingControl parkingControl) {
        return parkingControl.getLicensePlate().toUpperCase().startsWith("A");
    }

    public boolean isAvailableDay() {
        return calendarService.isMonday() || calendarService.isSunday() ;
    }
}
