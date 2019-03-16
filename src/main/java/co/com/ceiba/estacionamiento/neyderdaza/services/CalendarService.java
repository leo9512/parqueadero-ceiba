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

    public static Date getActualDate() {
        return Calendar.getInstance(locale).getTime();
    }

    public static boolean isMonday(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
}

    public static boolean isSunday(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
}
