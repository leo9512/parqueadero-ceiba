package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import co.com.ceiba.estacionamiento.neyderdaza.utils.ParkingControlException;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ParkingServiceTest {

    private static final String NO_AVAILABLE_CAR_CELLS = "Vehicle cannot enter, there are not more cells available for cars";
    private static final String NO_AVAILABLE_MOTORCYCLE_CELLS= "Vehicle cannot enter, there are not more cells available for motorcycles";

    private static final String NOT_ENTER_VEHICLE = "Vehicle cannot enter, license begin for A and today is not available day for it";

     ParkingService parkingService;
     ParkingControlRepository parkingControlRepository;
     ParkingCalculationService parkingCalculationService ;
     ParkingControl parkingControl;
     CalendarService calendarService;

    @Before
    public void setUp()  {
        calendarService = new CalendarService();
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);
    }

    @Test
    public void validateEntryConditionsForCar(){
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("EDS234")
                .withVehicleType("CAR")
                .withIsParking(false)
                .withEngine(200)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(21).when(parkingServiceSpy).howManyVehiclesAreParking( "CAR");
        //Act
        try {
            parkingServiceSpy.validateParkingConditions(parkingControl);
            fail("the vehicle can not enter because there are no available cells in the parking");
        }catch (ParkingControlException e) {
            //Assert
            assertEquals(NO_AVAILABLE_CAR_CELLS, e.getMessage());
        }
    }

    @Test
    public void validateEntryConditionsForMotorcycle(){
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("EDS234")
                .withVehicleType("MOTORCYCLE")
                .withIsParking(false)
                .withEngine(200)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(11).when(parkingServiceSpy).howManyVehiclesAreParking( "MOTORCYCLE");
        //Act
        try {
            parkingServiceSpy.validateParkingConditions(parkingControl);
            fail("the vehicle can not enter because there are no available cells in the parking");
        }catch (ParkingControlException e) {
            //Assert
            assertEquals(NO_AVAILABLE_MOTORCYCLE_CELLS, e.getMessage());
        }
    }

    @Test
    public void allowEntryWhenCellsAreAvailableForCars(){
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("EDS234")
                .withVehicleType("CAR")
                .withIsParking(false)
                .withEngine(200)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(19).when(parkingServiceSpy).howManyVehiclesAreParking( "CAR");
        try {
            //Act
            parkingServiceSpy.validateParkingConditions(parkingControl);
            //Assert
            assert(true);
        }catch (ParkingControlException e) {
            fail("he vehicle must be allowed to enter because there are more available cells.");
        }
    }

    @Test
    public void vehicleCanNotEnterIfLicenseBigintWithATheSundayAndMonday(){
        ParkingControl car = aParkingControl()
                .withLicensePlate("ACD123")
                .withVehicleType("CAR")
                .withEngine(150)
                .withIsParking(true)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(true).when(parkingServiceSpy).isNotAvailableDay();

        try {
            parkingServiceSpy.canEnterVehicle(car);
            fail("On Sunday or Monday, the cars whose license plate starts with \"A\" do not enter the parking");
        }catch (ParkingControlException e){
            assertEquals(NOT_ENTER_VEHICLE, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToADayIsDifferentToMondayAndSunday(){
        //Arrange
        ParkingControl car = aParkingControl()
                .withLicensePlate("ACD123")
                .withVehicleType("CAR")
                .withEngine(150)
                .withIsParking(true)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(false).when(parkingServiceSpy).isNotAvailableDay();

        //Act
        boolean result = parkingServiceSpy.canEnterVehicle(car);
        //Assert
        assertTrue(result);
    }

    @Test
    public void verifyVehiclesAreParking(){
        //Arrange
        List<ParkingControl> vehiclesInParkingExpected = new ArrayList<>();
        ParkingControl car1 = aParkingControl()
                .withLicensePlate("CVB567")
                .build();
        ParkingControl motorcicle1 = aParkingControl()
                .withVehicleType(Vehicles.MOTORCYCLE.toString())
                .build();
        ParkingControl car2 = aParkingControl()
                .build();
        vehiclesInParkingExpected.add(car1);
        vehiclesInParkingExpected.add(motorcicle1);
        vehiclesInParkingExpected.add(car2);

        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        given(parkingControlRepository.findByIsParking(true))
                .willReturn(vehiclesInParkingExpected);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);

        //Act
        List<ParkingControl> vehiclesInParkingResult = parkingService.findVehiclesAreParked();

        //Assert
        assertEquals(vehiclesInParkingExpected,vehiclesInParkingResult);
    }




}
