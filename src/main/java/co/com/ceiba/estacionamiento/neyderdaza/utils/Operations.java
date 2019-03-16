package co.com.ceiba.estacionamiento.neyderdaza.utils;

public class Operations {

    private static final int TOTAL_HOURS_PER_DAY = 24;
    private static final int HOUR_TO_BEGIN_A_DAY = 9;
    private static final int HOUR_TO_END_A_DAY = 23;
    private static final int CC_LIMIT = 500;
    private static final int MOTORCYCLE_VALUE_HOUR = 500;
    private static final int MOTORCYCLE_VALUE_DAY = 4000;
    private static final int EXTRA_MONEY_TO_PAY_FOR_CC_LIMIT = 2000;
    private static final int CAR_VALUE_DAY = 8000;
    private static final int CAR_VALUE_HOUR = 1000;
    private static final String MOTORCYCLE_TYPE = "MOTORCYCLE";
    private static final String CAR_TYPE = "CAR";

    private Operations() {
    }

    public static boolean hourIsBetweenNineAndTwentyFour(int parkingHours){
        return ((parkingHours % TOTAL_HOURS_PER_DAY)>=HOUR_TO_BEGIN_A_DAY && (parkingHours % TOTAL_HOURS_PER_DAY)<=HOUR_TO_END_A_DAY);
    }

    public static boolean motorcycleExceedsEngine(int engine){
        return engine>CC_LIMIT;
    }

    public static boolean isMotorcycle(String vehicleType){
        return vehicleType.equalsIgnoreCase(MOTORCYCLE_TYPE);
    }

    public static boolean isCar(String vehicleType){
        return vehicleType.equalsIgnoreCase(CAR_TYPE);
    }

    public static double calculateMotorcycleParkingWithExtraMoney(int daysToPay, int hoursToPay){
        return (double)(daysToPay * MOTORCYCLE_VALUE_DAY) + (hoursToPay * MOTORCYCLE_VALUE_HOUR) + EXTRA_MONEY_TO_PAY_FOR_CC_LIMIT;
    }

    public static double calculateMotorcycleParking(int daysToPay, int hoursToPay){
        return (double)(daysToPay * MOTORCYCLE_VALUE_DAY) + (hoursToPay * MOTORCYCLE_VALUE_HOUR);
    }

    public static double calculateCarParking(int daysToPay, int hoursToPay){
        return (double)(daysToPay * CAR_VALUE_DAY) + (hoursToPay * CAR_VALUE_HOUR);
    }

    public static int divisionPerTotalHourPerDay(int parkingHours){
        return parkingHours / TOTAL_HOURS_PER_DAY;
    }

    public static int modulePerTotalHourPerDay (int parkingHours){
        return parkingHours % TOTAL_HOURS_PER_DAY;
    }
}
