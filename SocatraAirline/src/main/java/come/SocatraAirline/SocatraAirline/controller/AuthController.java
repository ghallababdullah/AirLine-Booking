package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.LoginRequest;
import come.SocatraAirline.SocatraAirline.dtos.RegistrationRequest;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService ;
    @PostMapping("/register")

    public ResponseEntity<Response<?>> register (@Valid @RequestBody RegistrationRequest registrationRequest ){


        return ResponseEntity.ok(authService.register((registrationRequest))) ;
    }

    @PostMapping("/login")

    public ResponseEntity<Response<?>> login (@Valid @RequestBody LoginRequest loginRequest ){


        return ResponseEntity.ok(authService.login((loginRequest))) ;
    }




}
