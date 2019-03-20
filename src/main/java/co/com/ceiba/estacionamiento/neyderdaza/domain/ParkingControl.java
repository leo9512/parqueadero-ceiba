package co.com.ceiba.estacionamiento.neyderdaza.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ParkingControl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String vehicleDataArrived;
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

    private String vehicleDataOut;

    public ParkingControl(String vehicleDataArrived, String vehicleType, String licensePlate, int engine, boolean isParking) {
        this.vehicleDataArrived = vehicleDataArrived;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.isParking = isParking;
    }

    public ParkingControl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleDataArrived() {

        return vehicleDataArrived;
    }

    public void setVehicleDataArrived(String vehicleDataArrived) {
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

    public String getVehicleDataOut() {
        return vehicleDataOut;
    }

    public void setVehicleDataOut(String vehicleDataOut) {
        this.vehicleDataOut = vehicleDataOut;
    }
}
