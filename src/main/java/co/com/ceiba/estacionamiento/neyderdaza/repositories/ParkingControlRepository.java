package co.com.ceiba.estacionamiento.neyderdaza.repositories;

import co.com.ceiba.estacionamiento.neyderdaza.domain.ParkingControl;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ParkingControlRepository extends CrudRepository<ParkingControl,Long> {
     List<ParkingControl> findByIsParking(boolean isParking);
}
