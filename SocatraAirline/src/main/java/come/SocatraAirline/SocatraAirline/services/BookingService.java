package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.BookingDTO;
import come.SocatraAirline.SocatraAirline.dtos.CreateBookingRequest;
import come.SocatraAirline.SocatraAirline.dtos.CreateFlightRequest;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.enums.BookingStatus;

import java.util.List;

public interface BookingService {
    Response<?> createBooking(CreateBookingRequest createBookingRequest) ;

    Response<BookingDTO>getBookingById(Long id);

    Response<List<BookingDTO>> getAllBookings ();

    Response<List<BookingDTO>> getMyBookings();

    Response<?> updateBookingsStatus (Long id , BookingStatus status) ;
}
