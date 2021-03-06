package co.com.ceiba.estacionamiento.neyderdaza.controllers;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.dto.ParkingControlDTO;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingControlController {
    private ParkingService parkingService;

    public ParkingControlController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/vehiclesInParking")
    List<ParkingControl> getVehiclesParking(){
        return parkingService.findVehiclesAreParked();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/upDateVehicle/{id}")
    @ResponseBody
    ParkingControl outVehicle(@PathVariable Long id){
        ParkingControl parkingControl1  = parkingService.findVehicle(id);
         return parkingService.outsideVehicle(parkingControl1);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/addVehicle")
    ParkingControl enterVehicle(@RequestBody ParkingControlDTO dto){
        return parkingService.enterVehicle(dto);
    }
}
