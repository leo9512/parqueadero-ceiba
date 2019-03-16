package co.com.ceiba.estacionamiento.neyderdaza.dto;

import java.util.Date;

public class ParkingControlDTO {
        private Date vehicleDataArrived;
        private String vehicleType;
        private String licensePlate;
        private int engine;
        private boolean isParking;

    public Date getVehicleDataArrived() {
        return vehicleDataArrived;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEngine() {
        return engine;
    }

    public boolean isParking() {
        return isParking;
    }
}
