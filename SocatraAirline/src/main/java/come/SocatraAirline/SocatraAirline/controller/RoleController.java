package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.RoleDTO;
import come.SocatraAirline.SocatraAirline.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService ;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<?>> createRole(@Valid @RequestBody RoleDTO roleDTO ){


        return ResponseEntity.ok(roleService.createRole((roleDTO))) ;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<?>> updateRole(@Valid @RequestBody RoleDTO roleDTO ){


        return ResponseEntity.ok(roleService.createRole((roleDTO))) ;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    public  ResponseEntity<Response<?>> getRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }


}
