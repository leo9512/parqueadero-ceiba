package co.com.ceiba.estacionamiento.neyderdaza.controllers;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import co.com.ceiba.estacionamiento.neyderdaza.services.CalendarService;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingCalculationService;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ParkingControlControllerTest {
    ParkingControl parkingControl;
    ParkingService parkingService;
    CalendarService calendarService;
    ParkingCalculationService parkingCalculationService;
    ParkingControlRepository parkingControlRepository;
    private MockMvc mockMvc;
    @MockBean
    private ParkingControlController parkingControlController;

    @Before
    public void setUp() throws Exception {
        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);
        parkingControlController = new ParkingControlController(parkingService);
        mockMvc = MockMvcBuilders.standaloneSetup(parkingControlController).build();
    }

    @Test
    public void enterCarToParking() throws Exception {
       List<ParkingControl> listCarsParked = new ArrayList<>();
        parkingControl = aParkingControl().build();

        listCarsParked.add(parkingControl);

        given(parkingControlRepository.findByIsParking(true))
                .willReturn((listCarsParked));


        mockMvc.perform(get("/vehiclesInParking"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].licensePlate").value("ABC123"));

    }
}
