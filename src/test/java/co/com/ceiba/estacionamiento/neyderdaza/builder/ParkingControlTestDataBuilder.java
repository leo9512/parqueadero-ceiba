package co.com.ceiba.estacionamiento.neyderdaza.builder;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Operations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParkingControlTestDataBuilder {

    private Date vehicleDataArrived;
    private String vehicleType;
    private String licensePlate;
    private int engine;
    private boolean isParking;
    private int totalHours;
    private double valueToPay;
    private Date vehicleDataOut;

    private Calendar initialCalendarDate = new GregorianCalendar(2019,3, 14, 8, 40, 40);

    public ParkingControlTestDataBuilder(){
        this.vehicleDataArrived = initialCalendarDate.getTime();
        this.vehicleType = "CAR";
        this.licensePlate = "ABC123";
        this.engine = 600;
        this.isParking = true;

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
        if (Operations.isCar(vehicleType)){
            return new ParkingControl(vehicleDataArrived,vehicleType,licensePlate,engine,isParking);
        }
        return new ParkingControl(vehicleDataArrived,vehicleType,licensePlate,engine,isParking);
    }

    public static ParkingControlTestDataBuilder aParkingControl(){
        return new ParkingControlTestDataBuilder();
    }
}
