package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.*;
import come.SocatraAirline.SocatraAirline.enums.City;
import come.SocatraAirline.SocatraAirline.enums.Country;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import come.SocatraAirline.SocatraAirline.services.FlightService;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService ;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'PILOT')")
    public ResponseEntity<Response<?>> createFlight(@Valid @RequestBody CreateFlightRequest createFlightRequest ){

        return ResponseEntity.ok(flightService.createFlight((createFlightRequest))) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FlightDTO>> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping
    public ResponseEntity<Response<List<FlightDTO>>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }


    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'PILOT')")
    public ResponseEntity<Response<?>> updateflight( @RequestBody CreateFlightRequest flightRequest ){

        return ResponseEntity.ok(flightService.updateFlight((flightRequest))) ;
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<FlightDTO>>> searchFlight(
            @RequestParam(required = true) String departureIataCode ,
            @RequestParam(required = true) String arrivalIataCode ,
            @RequestParam(required = false , defaultValue = "SCHEDUALED") FlightStatus status ,
            @RequestParam @ DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate
    ) {


        return ResponseEntity.ok(flightService.searchFlight(departureIataCode , arrivalIataCode , status , departureDate));
    }
    @GetMapping("/cities")
    public ResponseEntity<Response<List<City>>> getAllCities() {
        return ResponseEntity.ok(flightService.getAllCities());
    }

    @GetMapping("/countries")
    public ResponseEntity<Response<List<Country>>> getAllCountry() {
        return ResponseEntity.ok(flightService.getAllCountry());
    }




}
