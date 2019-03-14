package co.com.ceiba.estacionamiento.neyderdaza.CeibaEstacionamiento.neyder.daza.services;

import co.com.ceiba.estacionamiento.neyderdaza.CeibaEstacionamiento.neyder.daza.operations.Operations;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParkingCalculations {

    private int TOTAL_HOURS_PER_DAY = 24;

    public int getHoursInParking(Date vehicleDateArrived, Date vehicleDateOuted){

        double diferenceInHours = ((double)vehicleDateOuted.getTime() - (double)vehicleDateArrived.getTime())/(60*60*1000);
        if((diferenceInHours%1)==0){return (int) diferenceInHours;}
        else {return (int) Math.ceil(diferenceInHours);}
    }

    public double getValueToPayInParking(int parkingHours, String vehicleType, int engine){

        double valueToPay = 0.0;
        int daysToPay = parkingHours / TOTAL_HOURS_PER_DAY;
        int hoursToPay = 0;

        if(Operations.hourIsBetweenNineAndTwentyFour(parkingHours)){
            daysToPay++;
        } else{
            hoursToPay = parkingHours % TOTAL_HOURS_PER_DAY;
        }
        if(Operations.isMotorcycle(vehicleType) && Operations.motorcycleExceedsEngine(engine)){
            valueToPay = Operations.calculateMotorcycleParkingWithExtraMoney(daysToPay,hoursToPay);
        }else {
            valueToPay = Operations.calculateMotorcycleParking(daysToPay,hoursToPay);
        }
        if(Operations.isCar(vehicleType)){
            valueToPay = Operations.calculateCarParking(daysToPay,hoursToPay);
        }
        return valueToPay;
    }
}
