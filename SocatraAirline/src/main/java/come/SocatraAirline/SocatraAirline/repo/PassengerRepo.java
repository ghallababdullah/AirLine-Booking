package come.SocatraAirline.SocatraAirline.repo;

import come.SocatraAirline.SocatraAirline.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger , Long> {
}
