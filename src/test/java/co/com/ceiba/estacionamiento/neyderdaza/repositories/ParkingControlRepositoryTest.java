package co.com.ceiba.estacionamiento.neyderdaza.repositories;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import co.com.ceiba.estacionamiento.neyderdaza.utils.Vehicles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static co.com.ceiba.estacionamiento.neyderdaza.builder.ParkingControlTestDataBuilder.aParkingControl;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest

public class ParkingControlRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParkingControlRepository parkingControlRepository;

    @Test
    public void verifyFindAllVehiclesAreParked() {
        ParkingControl car = aParkingControl()
                .withId(89L)
                .withLicensePlate("OLI098")
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
        String carDateArrived = "2019-mar-19 06:30:30";
        String carDateOuted = "2019-mar-19 12:30:30";
        ParkingControl carExpected = aParkingControl()
                .withId(40L)
                .withLicensePlate("POI095")
                .withVehicleType(Vehicles.CAR.toString())
                .withEngine(200)
                .withDateArrived(carDateArrived)
                .build();
        carExpected.setValueToPay(6000.0);
        carExpected.setVehicleDataOut(carDateOuted);
        carExpected.setParking(false);
        carExpected.setTotalHours(6);

        ParkingControl vehicleParked = parkingControlRepository.save(carExpected);
        assertThat(vehicleParked.getLicensePlate()).isEqualTo("POI095");
        assertThat(vehicleParked.getTotalHours()).isEqualTo(6);
        assertThat(vehicleParked.getEngine()).isEqualTo(200);
        assertThat(vehicleParked.getValueToPay()).isEqualTo(6000.0);
        assertThat(vehicleParked.getVehicleType()).isEqualTo(Vehicles.CAR.toString());
    }
}

