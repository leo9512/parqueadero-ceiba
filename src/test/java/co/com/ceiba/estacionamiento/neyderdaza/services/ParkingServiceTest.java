package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import co.com.ceiba.estacionamiento.neyderdaza.utils.ParkingControlException;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

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
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("ACD123")
                .withVehicleType("CAR")
                .withEngine(150)
                .withIsParking(true)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(true).when(parkingServiceSpy).isNotAvailableDay();

        try {
            //Act
            parkingServiceSpy.canEnterVehicle(parkingControl);
            fail("On Sunday or Monday, the cars whose license plate starts with \"A\" do not enter the parking");
        }catch (ParkingControlException e){
            //Assert
            assertEquals(NOT_ENTER_VEHICLE, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToADayIsDifferentToMondayAndSunday(){
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("ACD123")
                .withVehicleType("CAR")
                .withEngine(150)
                .withIsParking(true)
                .build();

        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(false).when(parkingServiceSpy).isNotAvailableDay();

        //Act
        boolean result = parkingServiceSpy.canEnterVehicle(parkingControl);
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

    @Test
    public void verifyQuantityOfParkedVehiclesIsEqualsToTwo(){
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
         int result = parkingService.howManyVehiclesAreParking(Vehicles.CAR.toString());

        //Assert
        assertEquals(2,result);
    }

    @Test
    public void verifyThatTodayIsNotAvailableDay(){

        //Arrange
        calendarService = Mockito.mock(CalendarService.class);
        given(calendarService.isMonday())
                .willReturn(false);
        given(calendarService.isSunday())
                .willReturn(false);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);

        //Act
        boolean result = parkingService.isNotAvailableDay();

        //Assert
        assertFalse(result);
    }

    @Test
    public void ifExistVehicleThenReturnThis(){
        //Arrange
        parkingControl = aParkingControl()
                .withId(30L)
                .withLicensePlate("CVB567")
                .build();

        Optional <ParkingControl> optionalCar = Optional.of(parkingControl);
        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        given(parkingControlRepository.findById(30L))
                .willReturn(optionalCar);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);

        //Act
        Optional<ParkingControl> result = parkingService.findVehicle(30L);

        //Assert
        assertEquals(parkingControl,result.get());
    }

    @Test
    public void ifTheVehicleDoesNotExistThenReturnVoid(){
        //Arrange
        Optional <ParkingControl> optionalCar = Optional.empty();
        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        given(parkingControlRepository.findById(30L))
                .willReturn(optionalCar);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);

        //Act
        Optional<ParkingControl> result = parkingService.findVehicle(30L);

        //Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void VerifyRemoveVehicleFromTheParking(){
        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,3, 19, 6, 30, 30);
        Calendar finalCalendarDate = new GregorianCalendar(2019, 3, 19, 12,30, 30);
        String carDateArrived = "2019-mar-19 6:30:30";
        String carDateOuted = "2019-mar-19 12:30:30";
        parkingControl = aParkingControl()
                .withId(40L)
                .withLicensePlate("POI095")
                .withVehicleType(Vehicles.CAR.toString())
                .withIsParking(true)
                .withEngine(200)
                .withDateArrived(carDateArrived)
                .build();

        ParkingControl carExpected = aParkingControl()
                .withId(40L)
                .withLicensePlate("POI095")
                .withVehicleType(Vehicles.CAR.toString())
                .withEngine(200)
                .withIsParking(false)
                .withDateArrived(carDateArrived)
                .withDataOut(carDateOuted)
                .withTotalHours(6)
                .withValueToPay(6000.0)
                .build();

        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        calendarService = Mockito.mock(CalendarService.class);
        parkingCalculationService = Mockito.mock(ParkingCalculationService.class);

        given(calendarService.getActualDate())
                .willReturn(carDateOuted);
        given(parkingCalculationService.getValueToPayInParking(10,"CAR",100))
                .willReturn(6000.0);
        given(parkingCalculationService.getHoursInParking(calendarService.stringToDate(carDateArrived),calendarService.stringToDate(carDateOuted)))
                .willReturn(6);
        given(parkingControlRepository.save(parkingControl))
                .willReturn(carExpected);

        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);
        //Act
        ParkingControl result = parkingService.outsideVehicle(parkingControl);

        //Assert
        assertEquals(carExpected,result);
    }

    @Test
    public void IfVehicleSatisfyTheEntryConditionsThenAllowEntry(){
        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("ACD123")
                .withVehicleType("CAR")
                .withEngine(150)
                .withIsParking(true)
                .build();

        parkingControlRepository = Mockito.mock(ParkingControlRepository.class);
        given(parkingControlRepository.save(parkingControl))
                .willReturn(parkingControl);
        parkingService = new ParkingService(parkingControlRepository, calendarService, parkingCalculationService);
        ParkingService parkingServiceSpy = Mockito.spy(parkingService);
        doReturn(true).when(parkingServiceSpy).validateParkingConditions(parkingControl);

        //Act
        ParkingControl result = parkingServiceSpy.enterVehicle(parkingControl);
        //Assert
        assertEquals(parkingControl,result);
    }
}
