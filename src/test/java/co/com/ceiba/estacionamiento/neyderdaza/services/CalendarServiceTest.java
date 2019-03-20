package co.com.ceiba.estacionamiento.neyderdaza.services;

import co.com.ceiba.estacionamiento.neyderdaza.utils.ParkingControlException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
public class CalendarServiceTest {

    @Mock
    CalendarService calendarService;

    @Before
    public void setUp(){
        calendarService = new CalendarService();
    }

    @Test
    public void verifyGetActualDayIsCorrectly(){
        //Arrange
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String expected = dateFormat.format(Calendar.getInstance().getTime());

        //Act
        String result = calendarService.getActualDate();

        //Assert
        assertEquals(expected,result);
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

    @Test
    public void verifyCorrectDateOfConverter(){
        //Arrange
        Calendar initialCalendarDate = new GregorianCalendar(2019,Calendar.MARCH, 19, 12, 30, 30);
        Date expected = initialCalendarDate.getTime();

        //Act
        Date result = calendarService.stringToDate("19-mar-2019 12:30:30");
        //Assert
        assertEquals(expected,result);
    }

    @Test
    public void verifyExceptionOfConverter(){

        try{
        calendarService.stringToDate("Soy un error");
        fail();
        }catch (ParkingControlException e){
            assertEquals("Invalidate date format", e.getMessage());
        }
    }
}
