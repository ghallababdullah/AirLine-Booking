package come.SocatraAirline.SocatraAirline.entities;

import come.SocatraAirline.SocatraAirline.enums.Passenger_Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String firstName;

    private String lastName;

    private String passportNumber;

    @Enumerated(EnumType.STRING)
    private Passenger_Type type;

    private String seatNumber;

    private String specialRequest;


}
