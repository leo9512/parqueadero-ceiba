package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.utils.ParkingControlException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class CalendarService {
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    protected CalendarService() {
    }

    private static Locale locale = new Locale("es", "CO");

    public  String getActualDate() {
        return dateFormat.format(Calendar.getInstance(locale).getTime());
    }

    public  boolean isMonday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public  boolean isSunday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
    public Date stringToDate(String date) {
        Date convert;
        try{
            convert = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new ParkingControlException("Invalidate date format");
        }
        return convert;
    }
}
