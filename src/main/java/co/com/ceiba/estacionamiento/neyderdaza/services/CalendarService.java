package co.com.ceiba.estacionamiento.neyderdaza.services;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class CalendarService {
    private DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    private static Locale locale = new Locale("es", "CO");

    public static Date getActualDate() {
        return Calendar.getInstance(locale).getTime();
    }

    public int getActualWeekDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
