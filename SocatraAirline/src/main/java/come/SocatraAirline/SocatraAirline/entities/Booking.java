package come.SocatraAirline.SocatraAirline.entities;

import come.SocatraAirline.SocatraAirline.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String bookingReference;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flight flight;

    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private  List<Passenger> Passengers = new ArrayList<>();


}
