package co.com.ceiba.estacionamiento.neyderdaza.utils;

import co.com.ceiba.estacionamiento.neyderdaza.services.CalendarService;
import co.com.ceiba.estacionamiento.neyderdaza.services.ParkingService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {
    Operations operations;
    @Before
    public void setUp()  {
        operations = new Operations();
    }

    @Test
    public void verifyThatVehicleIsInTheParkingBetweenNineAndTwentyFourHours(){
        boolean result = operations.hourIsBetweenNineAndTwentyFour(10);
        assertTrue(result);
    }
    @Test
    public void verifyThatVehicleIsNotInTheParkingBetweenNineAndTwentyFourHours(){
        boolean result = operations.hourIsBetweenNineAndTwentyFour(6);
        assertFalse(result);
    }
    @Test
    public void verifyThatVehicleIsInTheParkingSixHours(){
        boolean result = operations.hourIsBetweenNineAndTwentyFour(6);
        assertFalse(result);
    }
    @Test
    public void verifyThatMotorcycleExceedsEngine(){
        boolean result = operations.motorcycleExceedsEngine(600);
        assertTrue(result);
    }
    @Test
    public void verifyThatMotorcycleDoesNotExceedsEngine(){
        boolean result = operations.motorcycleExceedsEngine(400);
        assertFalse(result);
    }
    @Test
    public void verifyThatVehicleIsCar(){
        boolean result = operations.isCar(Vehicles.CAR.toString());
        assertTrue(result);
    }
    @Test
    public void verifyThatVehicleDoesNotCar(){
        boolean result = operations.isCar(Vehicles.MOTORCYCLE.toString());
        assertFalse(result);
    }
    @Test
    public void verifyThatVehicleIsMotorcycle(){
        boolean result = operations.isMotorcycle(Vehicles.MOTORCYCLE.toString());
        assertTrue(result);
    }
    @Test
    public void verifyThatVehicleDoesNotMotorcycle(){
        boolean result = operations.isMotorcycle(Vehicles.CAR.toString());
        assertFalse(result);
    }
    @Test
    public void calculateValueToPayForMotorcycleWhenThisExceedEngine(){
        double result = operations.calculateMotorcycleParkingWithExtraMoney(1,2);
        assertEquals(7000.0,result,0.0);
    }
    @Test
    public void calculatedValueToPayForCarForTwentySixHours(){
        double result = operations.calculateCarParking(1,2);
        assertEquals(10000.0,result,0.0);
    }
    @Test
    public void calculatedValueToPayForMotorcycleForTwentySixHours(){
        double result = operations.calculateMotorcycleParking(1,2);
        assertEquals(5000.0,result,0.0);
    }
    @Test
    public void verifyDivisionPerTotalHourPerDayWithNineParkingHours(){
        int result = operations.divisionPerTotalHourPerDay(9);
        assertEquals(0,result);
    }
    @Test
    public void verifyModulePerTotalHourPerDayWithNineParkingHours(){
        int result = operations.modulePerTotalHourPerDay(9);
        assertEquals(9,result);
    }
}
