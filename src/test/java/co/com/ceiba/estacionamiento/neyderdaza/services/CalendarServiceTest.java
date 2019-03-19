package co.com.ceiba.estacionamiento.neyderdaza.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class CalendarServiceTest {

    @Mock
    CalendarService calendarService;

    @Before
    public void setUp() throws Exception {
        calendarService = new CalendarService();
    }

    @Test
    public void verifyGetActualDayIsCorrectly(){
        //Arrange
        Locale localeDate = new Locale("es", "CO");
        Date expected = Calendar.getInstance(localeDate).getTime();

        //Act
        Date result = calendarService.getActualDate();

        //Assert
        assertEquals(expected.getDay(),result.getDay());
    }

    @Test
    public void verifyIsMonday(){
        //Arrange
        boolean expected = false;
        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 2){
            expected = true;
        }

        //Act
        boolean result = calendarService.isMonday();

        assertEquals(expected,result);
    }

    @Test
    public void verifyIsSunday(){
        //Arrange
        boolean expected = false;
        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1){
            expected = true;
        }

        //Act
        boolean result = calendarService.isSunday();

        assertEquals(expected,result);
    }
}
