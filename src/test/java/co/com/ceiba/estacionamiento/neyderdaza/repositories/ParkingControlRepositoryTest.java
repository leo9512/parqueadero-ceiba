package co.com.ceiba.estacionamiento.neyderdaza.repositories;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)

public class ParkingControlRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParkingControlRepository parkingControlRepository;

    @Test
    public void verifyFindAllVehiclesAreParked() {
        ParkingControl car = aParkingControl()
                .build();
        entityManager.persist(car);
        entityManager.flush();

        List<ParkingControl> vehiclesParked = parkingControlRepository.findByIsParking(true);
        assertThat(vehiclesParked.size()).isEqualTo(1);
        assertThat(vehiclesParked.get(0)).isEqualTo(car);

    }

    @Test
    public void verifyAddNewVehicle(){
        ParkingControl car = aParkingControl()
                .withLicensePlate("PLO435")
                .build();

        ParkingControl vehicleParked = parkingControlRepository.save(car);
        assertThat(vehicleParked.getLicensePlate()).isEqualTo("PLO435");
    }

    @Test
    public void verifyUpdateVehicle(){
        Calendar initialCalendarDate = new GregorianCalendar(2019,3, 19, 6, 30, 30);
        Calendar finalCalendarDate = new GregorianCalendar(2019, 3, 19, 12,30, 30);
        Date carDateArrived = initialCalendarDate.getTime();
        Date carDateOuted = finalCalendarDate.getTime();
        ParkingControl carExpected = aParkingControl()
                .withId(40L)
                .withLicensePlate("POI095")
                .withVehicleType(Vehicles.CAR.toString())
                .withEngine(200)
                .withIsParking(false)
                .withDateArrived(carDateArrived)
                .withDataOut(carDateOuted)
                .withTotalHours(6)
                .withValueToPay(6000.0)
                .buildComplete();

        ParkingControl vehicleParked = parkingControlRepository.save(carExpected);
        assertThat(vehicleParked.getLicensePlate()).isEqualTo("POI095");
        assertThat(vehicleParked.getTotalHours()).isEqualTo(6);
        assertThat(vehicleParked.getEngine()).isEqualTo(200);
        assertThat(vehicleParked.getValueToPay()).isEqualTo(6000.0);
        assertThat(vehicleParked.getVehicleType()).isEqualTo(Vehicles.CAR.toString());
    }
}

