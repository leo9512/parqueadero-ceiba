package co.com.ceiba.estacionamiento.neyderdaza.builder;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Operations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParkingControlTestDataBuilder {

    private Long id;
    private Date vehicleDataArrived;
    private String vehicleType;
    private String licensePlate;
    private int engine;
    private boolean isParking;
    private int totalHours;
    private double valueToPay;
    private Date vehicleDataOut;

    private Calendar initialCalendarDate = new GregorianCalendar(2019,3, 14, 8, 40, 40);
    private static final Operations operations = new Operations();

    public ParkingControlTestDataBuilder(){
        this.id = 20L;
        this.vehicleDataArrived = initialCalendarDate.getTime();
        this.vehicleType = "CAR";
        this.licensePlate = "ABC123";
        this.engine = 600;
        this.isParking = true;

    }

    public ParkingControlTestDataBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ParkingControlTestDataBuilder withDataOut(Date dateOut){
        this.vehicleDataOut = dateOut;
        return this;
    }

    public ParkingControlTestDataBuilder withTotalHours (int totalHours){
        this.totalHours = totalHours;
        return this;
    }

    public ParkingControlTestDataBuilder withValueToPay (double valueToPay){
        this.valueToPay = valueToPay;
        return this;
    }

    public ParkingControlTestDataBuilder withDateArrived(Date dateArrived){
        this.vehicleDataArrived = dateArrived;
        return this;
    }

    public ParkingControlTestDataBuilder withVehicleType(String vehicleType){
        this.vehicleType = vehicleType;
        return this;
    }

    public ParkingControlTestDataBuilder withLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
        return this;
    }

    public ParkingControlTestDataBuilder withEngine(int engine){
        this.engine = engine;
        return this;
    }

    public ParkingControlTestDataBuilder withIsParking(boolean isParking){
        this.isParking = isParking;
        return this;
    }

    public ParkingControl build(){
        if (operations.isCar(vehicleType)){
            return new ParkingControl(vehicleDataArrived,vehicleType,licensePlate,engine,isParking);
        }
        return new ParkingControl(vehicleDataArrived,vehicleType,licensePlate,engine,isParking);
    }

    public static ParkingControlTestDataBuilder aParkingControl(){
        return new ParkingControlTestDataBuilder();
    }
}
