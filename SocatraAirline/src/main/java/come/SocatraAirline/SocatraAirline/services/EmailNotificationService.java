package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.entities.Booking;
import come.SocatraAirline.SocatraAirline.entities.User;

public interface EmailNotificationService {
    void sendBookingTicketEmail (Booking booking);
    void sendWelcomeEmail(User user) ;

}
