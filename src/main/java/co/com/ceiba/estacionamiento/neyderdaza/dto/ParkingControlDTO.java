package co.com.ceiba.estacionamiento.neyderdaza.dto;


public class ParkingControlDTO {
        private String vehicleType;
        private String licensePlate;
        private int engine;


    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEngine() {
        return engine;
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

}
