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

    public void setVehicleDataArrived(String vehicleDataArrived) {
        this.vehicleDataArrived = vehicleDataArrived;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }
}
