package co.com.ceiba.estacionamiento.neyderdaza.dto;


public class ParkingControlDTO {
        private String vehicleDataArrived;
        private String vehicleType;
        private String licensePlate;
        private int engine;
        private boolean isParking;

    public String getVehicleDataArrived() {
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
