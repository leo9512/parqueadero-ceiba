package co.com.ceiba.estacionamiento.neyderdaza.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.*;
import static org.junit.Assert.assertEquals;

public class ParkingControlTest {

    @Mock
    ParkingControl parkingControl;
    @Test
    public void getVehicleLicensePlate(){

        //Arrange
        parkingControl = aParkingControl()
                .withLicensePlate("QWE963")
                .build();

        //Act
        String result = parkingControl.getLicensePlate();

        //Assert
        assertEquals("QWE963",result);
    }

    @Test
    public void getVehicleDataArrived(){

        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,12, 14, 8, 40, 40);
        parkingControl = aParkingControl()
                .withDateArrived(initialCalendarDate.getTime())
                .build();
        //Act
        Date result = parkingControl.getVehicleDataArrived();

        //Assert
        assertEquals(initialCalendarDate.getTime(),result);
    }

    @Test
    public void getVehicleEngine(){

        //Arrange
        parkingControl = aParkingControl()
                .withEngine(1600)
                .build();

        //Act
        int result = parkingControl.getEngine();

        //Assert
        assertEquals(1600,result);
    }

    @Test
    public void getVehicleIsParking(){

        //Arrange
        parkingControl = aParkingControl()
                .withIsParking(false)
                .build();

        //Act
        boolean result = parkingControl.isParking();

        //Assert
        assertEquals(false,result);
    }

    @Test
    public void getVehicleType(){

        //Arrange
        parkingControl = aParkingControl()
                .withVehicleType("MOTORCYCLE")
                .build();

        //Act
        String result = parkingControl.getVehicleType();

        //Assert
        assertEquals("MOTORCYCLE",result);
    }

    @Test
    public void setVehicleType(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setVehicleType("CAR");

        //Assert
        assertEquals("CAR",parkingControl.getVehicleType());
    }

    @Test
    public void setVehicleIsParking(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setParking(true);

        //Assert
        assertEquals(true,parkingControl.isParking());
    }

    @Test
    public void setVehicleEngine(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
         parkingControl.setEngine(1000);

        //Assert
        assertEquals(1000,parkingControl.getEngine());
    }

    @Test
    public void setVehicleDataArrived(){

        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,12, 14, 8, 40, 40);
        parkingControl = aParkingControl().build();
        //Act
        parkingControl.setVehicleDataArrived(initialCalendarDate.getTime());

        //Assert
        assertEquals(initialCalendarDate.getTime(),parkingControl.getVehicleDataArrived());
    }

    @Test
    public void setVehicleLicensePlate(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setLicensePlate("BNM789");

        //Assert
        assertEquals("BNM789",parkingControl.getLicensePlate());
    }

    @Test
    public void setVehicleTotalHours(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setTotalHours(9);

        //Assert
        assertEquals(9,parkingControl.getTotalHours());
    }

    @Test
    public void setVehicleDateOuted(){

        //Arrange
        Calendar finalCalendarDate = new GregorianCalendar(2019,12, 14, 15, 40, 40);
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setVehicleDataOut(finalCalendarDate.getTime());

        //Assert
        assertEquals(finalCalendarDate.getTime(),parkingControl.getVehicleDataOut());
    }

    @Test
    public void setVehicleValueToPay(){

        //Arrange
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setValueToPay(10000.0);

        //Assert
        assertEquals(10000.0,parkingControl.getValueToPay(),000.1);
    }
}
