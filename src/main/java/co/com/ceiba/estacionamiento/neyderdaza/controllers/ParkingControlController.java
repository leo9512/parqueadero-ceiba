package co.com.ceiba.estacionamiento.neyderdaza.controllers;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingControlController {
    private ParkingService parkingService;

    public ParkingControlController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @CrossOrigin
    @GetMapping("/vehiclesInParking")
    List<ParkingControl> getVehiclesParking(){
        return parkingService.findVehiclesAreParked();
    }

    @CrossOrigin
    @PatchMapping("/vehicles/{id}")
    ParkingControl outVehicle(@PathVariable Long id){
        ParkingControl parkingControl1 = parkingService.findVehicle(id).get();
        return parkingService.outsideVehicle(parkingControl1);
    }
}
