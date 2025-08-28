package come.SocatraAirline.SocatraAirline.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "email_notification")
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String subject;

    @NotBlank(message = "emailAdress Can't be Empty")
    private String emailAdress;

    @Lob
    private String body;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private LocalDateTime SentAt;
    private String RecipientEmail ;

    private boolean isHtml;


}
