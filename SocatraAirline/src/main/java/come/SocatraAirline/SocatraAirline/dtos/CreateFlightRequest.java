package come.SocatraAirline.SocatraAirline.dtos;

import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightRequest {

    private long id;
    private FlightStatus status;

    @NotBlank(message = "Flightnumber cannot be Blank")
    private String flightNumber;

    @NotBlank(message = "departur Airport IATA code cannot be Blank")
    private String departureAirportIataCode;

    @NotBlank(message = "Arrival Airport IATA code cannot be Blank")
    private String arrivalAirportIataCode;

    @NotNull(message = "Departure time cant be null")
    private LocalDateTime departureTime;

    @NotNull(message = "Arriavl time cant be null")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Base price cant be blank")
    @Positive(message = "Base price Must be Positive")
    private BigDecimal baseprice;
    @NotNull(message = "pilotID cant be blank")
    private Long pilotID;


}
