package co.com.ceiba.estacionamiento.neyderdaza.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class parkingCalculationsTest {

    @Mock
    ParkingCalculationService parkingCalculationService;

    @Before
    public void setUp() throws Exception {
        parkingCalculationService = new ParkingCalculationService();
    }

    @Test
    public void verifyExactSixHourInterval() {

        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,3, 14, 8, 40, 40);
        Date arrivedDate = initialCalendarDate.getTime();
        Calendar finalCalendarDate = new GregorianCalendar(2019,3, 14, 14, 40, 40);
        Date outedDate = finalCalendarDate.getTime();

        //Act
        int result = parkingCalculationService.getHoursInParking(arrivedDate, outedDate);

        //Assert
        assertEquals(6, result);
    }

    @Test
    public void verifyIntervalSixHoursWithTenMinutesEqualsSevenHours() {

        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,3, 14, 8, 40, 40);
        Date arrivedDate = initialCalendarDate.getTime();
        Calendar finalCalendarDate = new GregorianCalendar(2019,3, 14, 14, 50, 40);
        Date outedDate = finalCalendarDate.getTime();

        //Act
        int result = parkingCalculationService.getHoursInParking(arrivedDate, outedDate);

        //Assert
        assertEquals(7, result);
    }

    @Test
    public void calculateValueToPayForACarPerSixHours() {

        //Arrange
        int parkingHours = 6;
        int engine = 100;
        String vehicleType = "CAR";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(6000.0, result,0.001);
    }

    @Test
    public void calculateValueToPayForACarPerTwentyHours() {

        //Arrange
        int parkingHours = 20;
        int engine = 100;
        String vehicleType = "CAR";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(8000.0, result,0.001);
    }

    @Test
    public void calculateValueToPayForACarPerThirtyHours() {

        //Arrange
        int parkingHours = 30;
        int engine = 1600;
        String vehicleType = "CAR";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(14000.0, result,0.001);
    }

    @Test
    public void calculateValueToPayForAMotorcycleWith501CCPerSixHours() {

        //Arrange
        int parkingHours = 6;
        int engine = 501;
        String vehicleType = "MOTORCYCLE";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(5000.0, result,0.001);
    }
    @Test
    public void calculateValueToPayForAMotorcyclePerSixHours() {

        //Arrange
        int parkingHours = 6;
        int engine = 100;
        String vehicleType = "MOTORCYCLE";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(3000.0, result,0.001);
    }

    @Test
    public void calculateValueToPayForAMotorcyclePerTwentyHours() {

        //Arrange
        int parkingHours = 20;
        int engine = 100;
        String vehicleType = "MOTORCYCLE";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(4000.0, result,0.001);
    }

    @Test
    public void calculateValueToPayForAMotorcyclePerThirtyHours() {

        //Arrange
        int parkingHours = 30;
        int engine = 400;
        String vehicleType = "MOTORCYCLE";

        //Act
        double result = parkingCalculationService.getValueToPayInParking(parkingHours, vehicleType, engine);
        //Assert
        assertEquals(7000.0, result,0.001);
    }
}
