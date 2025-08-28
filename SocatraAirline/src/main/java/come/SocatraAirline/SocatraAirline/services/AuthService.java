package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.LoginRequest;
import come.SocatraAirline.SocatraAirline.dtos.LoginResponse;
import come.SocatraAirline.SocatraAirline.dtos.RegistrationRequest;
import come.SocatraAirline.SocatraAirline.dtos.Response;

public interface AuthService {
    Response<?> register(RegistrationRequest registrationRequest) ;
    Response<LoginResponse> login(LoginRequest loginRequest) ;

}
