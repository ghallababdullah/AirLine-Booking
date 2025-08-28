package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.UserDTO;
import come.SocatraAirline.SocatraAirline.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<Response<?>> updateMyAccount(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(userService.updateMyAccount((userDTO)));
    }

    @GetMapping("/pilots")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")

    public ResponseEntity<Response<List<UserDTO>>> getAllPilots() {
        return ResponseEntity.ok(userService.getAllPilot());
    }

    @GetMapping("/me")

    public ResponseEntity<Response<UserDTO>> getAccountDetails() {
        return ResponseEntity.ok(userService.getAccountDetails());
    }

}
