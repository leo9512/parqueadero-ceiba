package co.com.ceiba.estacionamiento.neyderdaza.controllers;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import co.com.ceiba.estacionamiento.neyderdaza.services.*;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;
import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ParkingControlControllerTest {
    ParkingControl parkingControl;
    ParkingService parkingService;
    CalendarService calendarService;
    ParkingCalculationService parkingCalculationService;
    ParkingControlRepository parkingControlRepository =  Mockito.mock(ParkingControlRepository.class);
    private MockMvc mockMvc;
    private ParkingControlController parkingControlController;

    @Before
    public void setUp() {
        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        calendarService = Mockito.mock(CalendarService.class);
        parkingCalculationService = Mockito.mock(ParkingCalculationService.class);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);
        parkingControlController = new ParkingControlController(parkingService);
        mockMvc = MockMvcBuilders.standaloneSetup(parkingControlController).build();
    }

    @Test
    public void consultVehiclesParked() throws Exception {
       List<ParkingControl> listCarsParked = new ArrayList<>();
        parkingControl = aParkingControl().build();

        listCarsParked.add(parkingControl);

        given(parkingControlRepository.findByIsParking(true))
                .willReturn((listCarsParked));


        mockMvc.perform(get("/vehiclesInParking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].licensePlate").value("ABC123"));

    }

    @Test
    public void enterVehicleToParking() throws Exception {
        //Arrange
        Gson gson = new Gson();
        parkingControl = aParkingControl()
                .withId(21L)
                .withDateArrived("2019-03-19 12:30:30")
                .withEngine(200)
                .withLicensePlate("JKL897")
                .withIsParking(true)
                .build();
        String jsonToSend = gson.toJson(parkingControl);

        when(parkingControlRepository.save(Mockito.any(ParkingControl.class))).thenReturn(parkingControl);
        //Act
        mockMvc.perform(post("/addVehicle")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonToSend))
                .andExpect(status().isCreated())
                //Assert
                .andExpect(jsonPath("$.licensePlate").value("JKL897"));
    }

    @Test
    public void exitCarOfParking() throws Exception {
        //Arrange
        ParkingControl parkingControlEnter = aParkingControl()
                .withId(30L)
                .withVehicleType(Vehicles.CAR.toString())
                .withDateArrived("2019-03-19 12:30:30")
                .withEngine(200)
                .withLicensePlate("POL675")
                .withIsParking(true)
                .build();
        Optional<ParkingControl> optionalParkingControlEnter = Optional.of(parkingControlEnter);

        ParkingControl parkingControlExit = aParkingControl()
                .withId(30L)
                .withVehicleType(Vehicles.CAR.toString())
                .withDateArrived("2019-03-19 6:30:30")
                .withEngine(200)
                .withLicensePlate("POL675")
                .build();
        parkingControlExit.setValueToPay(6000.0);
        parkingControlExit.setVehicleDataOut("2019-03-19 12:30:30");
        parkingControlExit.setParking(false);
        parkingControlExit.setTotalHours(6);


        when(parkingControlRepository.findById(Mockito.anyLong())).thenReturn(optionalParkingControlEnter);
        when(parkingControlRepository.save(Mockito.any(ParkingControl.class))).thenReturn(parkingControlExit);
        when(calendarService.getActualDate()).thenReturn("2019-03-19 12:30:30");
        when(calendarService.stringToDate(Mockito.anyString())).thenReturn(new Date());
        when(parkingCalculationService.getHoursInParking(Mockito.any(Date.class),Mockito.any(Date.class))).thenReturn(6);
        when(parkingCalculationService.getValueToPayInParking(Mockito.anyInt(),Mockito.anyString(), Mockito.anyInt())).thenReturn(6000.0);

        //Act
        mockMvc.perform(patch("/upDateVehicle/1"))
                .andExpect(status().isOk())
                //Assert
                .andExpect(jsonPath("$.licensePlate").value("POL675"))
                .andExpect(jsonPath("$.valueToPay").value("6000.0"))
                .andExpect(jsonPath("$.totalHours").value("6"))
                .andExpect(jsonPath("$.parking").value("false"));
    }

    @Test
    public void exitNonExistentCarInParking() throws Exception {
        //Arrange
        Optional<ParkingControl> optionalParkingControlEnter = Optional.empty();

        when(parkingControlRepository.findById(Mockito.anyLong())).thenReturn(optionalParkingControlEnter);

            //Act
        MvcResult result = mockMvc.perform(patch("/upDateVehicle/1")) .andReturn();
        String content = result.getResponse().getContentAsString();
        Gson transformer = new Gson();
        ParkingControl p = transformer.fromJson(content, ParkingControl.class);
        System.out.println(p.getVehicleType());
        //Assert
        assertTrue(p.getVehicleType() == null);
        assertTrue(p.getLicensePlate()==null);
        assertTrue(p.getVehicleDataArrived()==null);
        assertTrue(p.getVehicleDataOut()==null);
        assertTrue(p.isParking()==false);
        assertTrue(p.getId()==null);
    }
}
