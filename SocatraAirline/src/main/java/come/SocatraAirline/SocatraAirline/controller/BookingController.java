package come.SocatraAirline.SocatraAirline.controller;

import come.SocatraAirline.SocatraAirline.dtos.AirportDTO;
import come.SocatraAirline.SocatraAirline.dtos.BookingDTO;
import come.SocatraAirline.SocatraAirline.dtos.CreateBookingRequest;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.enums.BookingStatus;
import come.SocatraAirline.SocatraAirline.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService ;

    @PostMapping
    public ResponseEntity<Response<?>> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest ){


        return ResponseEntity.ok(bookingService.createBooking(createBookingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BookingDTO>> getBookingByID(@PathVariable Long id  ){
        return ResponseEntity.ok(bookingService.getBookingById(id)) ;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','PILOT')")
    public ResponseEntity<Response<List<BookingDTO>>> getAllBooking( ){
        return ResponseEntity.ok(bookingService.getAllBookings()) ;
    }

    @GetMapping("/me")
    public ResponseEntity<Response<List<BookingDTO>>> getMyBookings( ){
        return ResponseEntity.ok(bookingService.getMyBookings()) ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PILOT')")
    public ResponseEntity<Response<?>> updateBooking(@Valid @PathVariable Long id , @RequestBody BookingStatus status){

        return ResponseEntity.ok(bookingService.updateBookingsStatus(id , status ));
    }

}
