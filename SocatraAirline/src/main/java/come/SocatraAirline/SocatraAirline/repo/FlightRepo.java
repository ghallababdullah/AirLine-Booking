package come.SocatraAirline.SocatraAirline.repo;

import come.SocatraAirline.SocatraAirline.entities.Flight;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepo extends JpaRepository<Flight , Long> {

    Boolean existsByFlightNumber (String flightnumber);

    List<Flight> findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndStatusAndDepartureTimeBetween(
            String departureIataCode, String arrivalIataCode, FlightStatus status, LocalDateTime startOfDay, LocalDateTime endOfDay);}