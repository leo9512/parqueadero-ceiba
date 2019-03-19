package co.com.ceiba.estacionamiento.neyderdaza.services;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class CalendarService {

    protected CalendarService() {
    }

    private static Locale locale = new Locale("es", "CO");

    public  Date getActualDate() {
        return Calendar.getInstance(locale).getTime();
    }

    public  boolean isMonday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public  boolean isSunday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
}
