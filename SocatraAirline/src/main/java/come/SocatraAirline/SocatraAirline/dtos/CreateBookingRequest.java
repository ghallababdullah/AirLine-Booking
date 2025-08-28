package come.SocatraAirline.SocatraAirline.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    @NotNull(message = "Flight id can not be null")
    private Long flightId;

    @NotEmpty(message = "At lease one passenger be provided")
    private List<PassengerDTO> passengers;


}
