package come.SocatraAirline.SocatraAirline.entities;

import come.SocatraAirline.SocatraAirline.enums.City;
import come.SocatraAirline.SocatraAirline.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated
    private City city;

    @Column(nullable = false)
    @Enumerated
    private Country country;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;
}
