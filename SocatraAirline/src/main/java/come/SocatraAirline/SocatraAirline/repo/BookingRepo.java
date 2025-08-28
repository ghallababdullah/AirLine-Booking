package come.SocatraAirline.SocatraAirline.repo;

import come.SocatraAirline.SocatraAirline.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking , Long> {

    List<Booking>findByUserIdOrderByIdDesc(Long useriId) ;
}
