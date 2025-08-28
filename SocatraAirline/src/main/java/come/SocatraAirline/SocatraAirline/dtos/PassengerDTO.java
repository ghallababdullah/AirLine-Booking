package come.SocatraAirline.SocatraAirline.dtos;

import come.SocatraAirline.SocatraAirline.enums.Passenger_Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {


    private long id;

    @NotBlank(message = "firstName Can't be Empty")
    private String firstName;
    @NotBlank(message = "lasstName Can't be Empty")
    private String lasstName;
    @NotBlank(message = "passportNumber  Can't be Empty")
    private String passportNumber;

    @NotNull(message = "Passenger_Type Can't be Empty")
    private Passenger_Type type;

    private String seatnNumber;

    private String specialRequest;
}
