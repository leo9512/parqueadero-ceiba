package co.com.ceiba.estacionamiento.neyderdaza.services;

import org.springframework.stereotype.Service;

import java.util.Date;

import static co.com.ceiba.estacionamiento.neyderdaza.utils.Operations.*;

@Service
public class ParkingCalculations {
    private ParkingCalculations() {
    }

    public static int getHoursInParking(Date vehicleDateArrived, Date vehicleDateOuted){

        double diferenceInHours = ((double)vehicleDateOuted.getTime() - (double)vehicleDateArrived.getTime())/(60*60*1000);
        if((diferenceInHours%1)==0){return (int) diferenceInHours;}
        else {return (int) Math.ceil(diferenceInHours);}
    }

    public static double getValueToPayInParking(int parkingHours, String vehicleType, int engine){

        double valueToPay;
        int daysToPay = divisionPerTotalHourPerDay(parkingHours);
        int hoursToPay = 0;

        if(hourIsBetweenNineAndTwentyFour(parkingHours)){
            daysToPay++;
        } else{
            hoursToPay = modulePerTotalHourPerDay(parkingHours);
        }
        if(isMotorcycle(vehicleType) && motorcycleExceedsEngine(engine)){
            valueToPay = calculateMotorcycleParkingWithExtraMoney(daysToPay,hoursToPay);
        }else {
            valueToPay = calculateMotorcycleParking(daysToPay,hoursToPay);
        }
        if(isCar(vehicleType)){
            valueToPay = calculateCarParking(daysToPay,hoursToPay);
        }
        return valueToPay;
    }
}
