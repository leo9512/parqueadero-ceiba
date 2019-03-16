package co.com.ceiba.estacionamiento.neyderdaza.bootstrap;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.repositories.ParkingControlRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ParkingControlRepository parkingControlRepository;

    public DevBootstrap(ParkingControlRepository parkingControlRepository){
        this.parkingControlRepository = parkingControlRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }

    private void initData(){
        //CAR ONE
        Calendar initialCalendarDate = new GregorianCalendar(2019,3, 14, 8, 40, 40);
        Date carDateArrived = initialCalendarDate.getTime();
        ParkingControl vehicleOne = new ParkingControl(carDateArrived,"CAR","QWE123",200,true);
        parkingControlRepository.save(vehicleOne);

        ParkingControl vehicleTwo = new ParkingControl(carDateArrived,"CAR","ABC098",200,false);
        parkingControlRepository.save(vehicleTwo);

        ParkingControl vehicleThree = new ParkingControl(carDateArrived,"MOTORCYCLE","TYC654",200,true);
        parkingControlRepository.save(vehicleThree);

    }
}
