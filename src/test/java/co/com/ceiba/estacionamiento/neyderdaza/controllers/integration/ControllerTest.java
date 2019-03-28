package co.com.ceiba.estacionamiento.neyderdaza.controllers.integration;


import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ParkingControlRepository parkingControlRepository;

    @Test
    public void verifyAllTheVehiclesThatAreInTheParking() throws Exception {
        ParkingControl vehicle = aParkingControl().build();
        vehicle = parkingControlRepository.save(vehicle);
        System.out.println(vehicle);
        this.mockMvc.perform(get("/vehiclesInParking")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ABC123")));
    }

    @Test
    public void verifyTheVehicleHasBeenAddedCorrectly() throws Exception {
        Gson gson = new Gson();
        ParkingControl parkingControl = aParkingControl()
                .withId(21L)
                .withEngine(200)
                .withLicensePlate("JKL897")
                .withIsParking(true)
                .build();
        String jsonToSend = gson.toJson(parkingControl);

        this.mockMvc.perform(post("/addVehicle")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonToSend))
                .andDo(print())
                .andExpect(status().isCreated())
                //Assert
                .andExpect(jsonPath("$.licensePlate").value("JKL897"));
    }
    @Test
    public void verifyTheCorrectExitOfTheVehicle() throws Exception {
        ParkingControl vehicle = aParkingControl()
                .withLicensePlate("POI956")
                .build();
        vehicle = parkingControlRepository.save(vehicle);
        String id = vehicle.getId().toString();

        mockMvc.perform(patch("/upDateVehicle/"+id))
                .andExpect(status().isOk())
                //Assert
                .andDo(print())
                .andExpect(jsonPath("$.licensePlate").value("POI956"))
                .andExpect(jsonPath("$.parking").value("false"));
    }
}
