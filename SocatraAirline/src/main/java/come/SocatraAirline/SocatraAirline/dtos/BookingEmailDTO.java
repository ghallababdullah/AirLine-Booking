package come.SocatraAirline.SocatraAirline.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEmailDTO {
    private String userName;
    private String bookingReference;
    private String flightNumber;
    private String departureAirportIataCode;
    private String arrivalAirportIataCode;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal basePrice;
    private List<PassengerDTO> passengers;
}
