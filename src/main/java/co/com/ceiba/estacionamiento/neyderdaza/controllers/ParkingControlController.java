package co.com.ceiba.estacionamiento.neyderdaza.controllers;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.dto.ParkingControlDTO;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ParkingControlController {
    private ParkingService parkingService;

    public ParkingControlController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/vehiclesInParking")
    List<ParkingControl> getVehiclesParking(){
        return parkingService.findVehiclesAreParked();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/upDateVehicle/{id}")
    @ResponseBody
    ParkingControl outVehicle(@PathVariable Long id){
        ParkingControl parkingControl1 = new ParkingControl();
        Optional<ParkingControl> parkingConsult = parkingService.findVehicle(id);
        if(parkingConsult.isPresent()){
            parkingControl1= parkingConsult.get();
        }
        else {
            return parkingControl1;
        }

        return parkingService.outsideVehicle(parkingControl1);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/addVehicle")
    ParkingControl enterVehicle(@RequestBody ParkingControlDTO dto){
        ParkingControl parkingControl = new ParkingControl();
        parkingControl.setVehicleDataArrived(dto.getVehicleDataArrived());
        parkingControl.setEngine(dto.getEngine());
        parkingControl.setParking(dto.isParking());
        parkingControl.setVehicleType(dto.getVehicleType());
        parkingControl.setLicensePlate(dto.getLicensePlate());
        return parkingService.enterVehicle(parkingControl);
    }
}
