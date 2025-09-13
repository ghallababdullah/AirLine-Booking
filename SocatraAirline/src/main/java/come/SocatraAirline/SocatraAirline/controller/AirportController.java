package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.AirportDTO;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.RoleDTO;
import come.SocatraAirline.SocatraAirline.services.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService ;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'PILOT')")
    public ResponseEntity<Response<?>> createAirport(@Valid @RequestBody AirportDTO airportDTO ){


        return ResponseEntity.ok(airportService.createAirport((airportDTO))) ;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'PILOT')")
    public ResponseEntity<Response<?>> updateAirport( @RequestBody AirportDTO airportDTO ){


        return ResponseEntity.ok(airportService.updateAirport((airportDTO))) ;
    }

    @GetMapping
    public ResponseEntity<Response<List<AirportDTO>>> getAllAirport(  ){
        return ResponseEntity.ok(airportService.getAllAirports()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AirportDTO>> getAirportByID(@PathVariable Long id  ){
        return ResponseEntity.ok(airportService.getAirportByID(id)) ;
    }

}
