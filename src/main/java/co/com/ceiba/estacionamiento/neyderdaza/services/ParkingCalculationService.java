package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.utils.Operations;
import org.springframework.stereotype.Service;

import java.util.Date;

import static co.com.ceiba.estacionamiento.neyderdaza.utils.Operations.*;

@Service
public class ParkingCalculationService {
    private static Operations operations = new Operations();
    protected ParkingCalculationService() {
    }

    public int getHoursInParking(Date vehicleDateArrived, Date vehicleDateOuted){

        double diferenceInHours = ((double)vehicleDateOuted.getTime() - (double)vehicleDateArrived.getTime())/(60*60*1000);
        if((diferenceInHours%1)==0){
            return (int) diferenceInHours;
        } else {
            return (int) Math.ceil(diferenceInHours);
        }
    }

    public double getValueToPayInParking(int parkingHours, String vehicleType, int engine){

        double valueToPay;
        int daysToPay = operations.divisionPerTotalHourPerDay(parkingHours);
        int hoursToPay = 0;

        if(operations.hourIsBetweenNineAndTwentyFour(parkingHours)){
            daysToPay++;
        } else{
            hoursToPay = operations.modulePerTotalHourPerDay(parkingHours);
        }
        if(operations.isMotorcycle(vehicleType) && operations.motorcycleExceedsEngine(engine)){
            valueToPay = operations.calculateMotorcycleParkingWithExtraMoney(daysToPay,hoursToPay);
        }else {
            valueToPay = operations.calculateMotorcycleParking(daysToPay,hoursToPay);
        }
        if(operations.isCar(vehicleType)){
            valueToPay = operations.calculateCarParking(daysToPay,hoursToPay);
        }
        return valueToPay;
    }
}
