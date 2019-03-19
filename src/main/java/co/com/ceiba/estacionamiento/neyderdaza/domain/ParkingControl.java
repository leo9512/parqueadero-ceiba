package co.com.ceiba.estacionamiento.neyderdaza.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ParkingControl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Date vehicleDataArrived;
    @NotNull
    private String vehicleType;
    @NotNull
    private String licensePlate;
    @NotNull
    private int engine;
    @NotNull
    private boolean isParking;

    private int totalHours;

    private double valueToPay;

    private Date vehicleDataOut;

    public ParkingControl(Date vehicleDataArrived, String vehicleType, String licensePlate, int engine, boolean isParking) {
        this.vehicleDataArrived = vehicleDataArrived;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.isParking = isParking;
    }

    public ParkingControl(Long id, Date vehicleDataArrived, String vehicleType, String licensePlate,int engine, boolean isParking, int totalHours, double valueToPay, Date vehicleDataOut) {
        this.id = id;
        this.vehicleDataArrived = vehicleDataArrived;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.isParking = isParking;
        this.totalHours = totalHours;
        this.valueToPay = valueToPay;
        this.vehicleDataOut = vehicleDataOut;
    }

    public ParkingControl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVehicleDataArrived() {

        return vehicleDataArrived;
    }

    public void setVehicleDataArrived(Date vehicleDataArrived) {
        this.vehicleDataArrived = vehicleDataArrived;
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
