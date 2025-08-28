package come.SocatraAirline.SocatraAirline.dtos;

import come.SocatraAirline.SocatraAirline.entities.Airport;
import come.SocatraAirline.SocatraAirline.entities.User;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private long id;

    private String flightNumber;

    private FlightStatus FlightStatus;

    private Airport departureAirport;

    private Airport ArrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal basePrice;


    private User assignedPilot;


    private List<BookingDTO> bookings;

    private String departureAirportIataCode;
    private String ArrivalAirportAirportIataCode;


}
