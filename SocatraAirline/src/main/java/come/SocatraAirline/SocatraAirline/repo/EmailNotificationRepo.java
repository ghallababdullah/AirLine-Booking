package come.SocatraAirline.SocatraAirline.repo;

import come.SocatraAirline.SocatraAirline.entities.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepo extends JpaRepository<EmailNotification , Long > {



}
