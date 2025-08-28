package come.SocatraAirline.SocatraAirline.services.imple;

import come.SocatraAirline.SocatraAirline.dtos.BookingDTO;
import come.SocatraAirline.SocatraAirline.dtos.CreateBookingRequest;
import come.SocatraAirline.SocatraAirline.dtos.PassengerDTO;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.entities.Booking;
import come.SocatraAirline.SocatraAirline.entities.Flight;
import come.SocatraAirline.SocatraAirline.entities.Passenger;
import come.SocatraAirline.SocatraAirline.entities.User;
import come.SocatraAirline.SocatraAirline.enums.BookingStatus;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import come.SocatraAirline.SocatraAirline.exceptions.BadRequestException;
import come.SocatraAirline.SocatraAirline.exceptions.NotFoundException;
import come.SocatraAirline.SocatraAirline.repo.BookingRepo;
import come.SocatraAirline.SocatraAirline.repo.FlightRepo;
import come.SocatraAirline.SocatraAirline.repo.PassengerRepo;
import come.SocatraAirline.SocatraAirline.services.BookingService;
import come.SocatraAirline.SocatraAirline.services.EmailNotificationService;
import come.SocatraAirline.SocatraAirline.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final ModelMapper modelMapper ;
    private final BookingRepo bookingRepo ;
    private final UserService userService  ;
    private final FlightRepo flightRepo ;
    private  final PassengerRepo passengerRepo ;
    private final EmailNotificationService emailNotificationService ;


    @Override
    public Response<?> createBooking(CreateBookingRequest createBookingRequest) {

        User user = userService.currentUser();

        Flight flight = flightRepo.findById(createBookingRequest.getFlightId())
                .orElseThrow(()-> new NotFoundException("Flight Not Found"));

        if (flight.getStatus() != FlightStatus.SCHEDUALED){
            throw new BadRequestException("You can only book a flight that is scheduled");
        }

        Booking booking = new Booking();
        booking.setBookingReference(generateBookingReference());
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepo.save(booking);

        if (createBookingRequest.getPassengers() != null && !createBookingRequest.getPassengers().isEmpty()){

            List<Passenger> passengers = createBookingRequest.getPassengers().stream()
                    .map(passengerDTO -> {
                        Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
                        passenger.setBooking(savedBooking);
                        return passenger;
                    }).toList();

            passengerRepo.saveAll(passengers);
            savedBooking.setPassengers(passengers);
        }

        //SEND EMAIL TICKER OUT
        emailNotificationService.sendBookingTicketEmail(savedBooking);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Booking created successfully")
                .build();

    }

    @Override
    public Response<BookingDTO> getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id).orElseThrow(()-> new NotFoundException("Booking Not Found"));
        BookingDTO bookingDTO = modelMapper.map(booking,BookingDTO.class);
        bookingDTO.getFlight().setBookings(null);

        return Response.<BookingDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .data(bookingDTO)
                .build() ;

    }

    @Override
    public Response<List<BookingDTO>> getAllBookings() {

        List<Booking> allBookings = bookingRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));

        List<BookingDTO> bookings= allBookings.stream()
                .map(booking -> {
                    BookingDTO bookingDTO = modelMapper.map(booking , BookingDTO.class);
                    bookingDTO.getFlight().setBookings(null);
                    return bookingDTO ;
                }).toList();
        return Response.<List<BookingDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(bookings.isEmpty()?"No Booking Found" : "Booking rerieved successfully")
                .data(bookings)
                .build() ;

    }

    @Override
    public Response<List<BookingDTO>> getMyBookings() {
        User user = userService.currentUser() ;
        List<Booking> userBookings = bookingRepo.findByUserIdOrderByIdDesc(user.getId());
        List<BookingDTO> bookings= userBookings.stream()
                .map(booking -> {
                    BookingDTO bookingDTO = modelMapper.map(booking , BookingDTO.class);
                    bookingDTO.getFlight().setBookings(null);
                    return bookingDTO ;
                }).toList();
        return Response.<List<BookingDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(bookings.isEmpty()?"No Booking Found for this user" : "User's Booking rerieved successfully")
                .data(bookings)
                .build() ;
    }
    @Transactional
    @Override
    public Response<?> updateBookingsStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Booking not Found"));
        booking.setStatus(status);
        bookingRepo.save(booking);
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message( " Booking updated successfully")
                .build() ;
    }

    //implement to make sure the booking reference does not already exist
    private String generateBookingReference(){

        return UUID.randomUUID().toString().substring(0 ,10).toUpperCase();
    }
}
