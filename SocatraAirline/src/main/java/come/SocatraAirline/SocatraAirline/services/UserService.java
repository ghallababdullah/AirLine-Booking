package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.UserDTO;
import come.SocatraAirline.SocatraAirline.entities.User;

import java.util.List;

public interface UserService {
    User currentUser () ;
    Response<?> updateMyAccount(UserDTO userDTO ) ;

    Response<List<UserDTO>> getAllPilot();

    Response<UserDTO> getAccountDetails();


}
