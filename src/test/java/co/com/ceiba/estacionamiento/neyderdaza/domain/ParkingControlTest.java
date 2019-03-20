package co.com.ceiba.estacionamiento.neyderdaza.domain;

import org.junit.Test;
import org.mockito.Mock;
import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
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
        parkingControl = aParkingControl()
                .withDateArrived("2019-mar-20 07:12:39")
                .build();
        //Act
        String result = parkingControl.getVehicleDataArrived();

        //Assert
        assertEquals("2019-mar-20 07:12:39",result);
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
        parkingControl = aParkingControl().build();
        //Act
        parkingControl.setVehicleDataArrived("2019-mar-20 07:12:39");

        //Assert
        assertEquals("2019-mar-20 07:12:39",parkingControl.getVehicleDataArrived());
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
        parkingControl = aParkingControl().build();

        //Act
        parkingControl.setVehicleDataOut("2019-mar-20 07:12:39");

        //Assert
        assertEquals("2019-mar-20 07:12:39",parkingControl.getVehicleDataOut());
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
