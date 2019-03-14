package co.com.ceiba.estacionamiento.neyderdaza.CeibaEstacionamiento.neyder.daza.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.UUID;

@Entity
public class ParkingControl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id = UUID.randomUUID().toString();
    @NotNull
    private Date vehicleDataArrive;
    @NotNull
    private String vehicleType;
    @NotNull
    private String licensePlate;
    @NotNull
    private int engine;
    @NotNull
    private boolean isParking;
    @Null
    private int totalHours;
    @Null
    private double valueToPay;
    @Null
    private Date vehicleDataOut;

    public ParkingControl(String id, Date vehicleDataArrive, String vehicleType, String licensePlate, int engine, boolean isParking) {
        this.id = id;
        this.vehicleDataArrive = vehicleDataArrive;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.isParking = isParking;
    }

    public ParkingControl() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVehicleDataArrive() {
        return vehicleDataArrive;
    }

    public void setVehicleDataArrive(Date vehicleDataArrive) {
        this.vehicleDataArrive = vehicleDataArrive;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public double getValueToPay() {
        return valueToPay;
    }

    public void setValueToPay(double valueToPay) {
        this.valueToPay = valueToPay;
    }

    public Date getVehicleDataOut() {
        return vehicleDataOut;
    }

    public void setVehicleDataOut(Date vehicleDataOut) {
        this.vehicleDataOut = vehicleDataOut;
    }
}
