package come.SocatraAirline.SocatraAirline.services.imple;

import come.SocatraAirline.SocatraAirline.dtos.CreateFlightRequest;
import come.SocatraAirline.SocatraAirline.dtos.FlightDTO;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.entities.Airport;
import come.SocatraAirline.SocatraAirline.entities.Flight;
import come.SocatraAirline.SocatraAirline.entities.User;
import come.SocatraAirline.SocatraAirline.enums.City;
import come.SocatraAirline.SocatraAirline.enums.Country;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;
import come.SocatraAirline.SocatraAirline.exceptions.BadRequestException;
import come.SocatraAirline.SocatraAirline.exceptions.NotFoundException;
import come.SocatraAirline.SocatraAirline.repo.AirportRepo;
import come.SocatraAirline.SocatraAirline.repo.FlightRepo;
import come.SocatraAirline.SocatraAirline.repo.UserRepo;
import come.SocatraAirline.SocatraAirline.services.FlightService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepo flightRepo ;
    private final AirportRepo airportRepo ;
    private final UserRepo userRepo ;
    private final ModelMapper modelMapper ;



    @Override
    public Response<?> createFlight(CreateFlightRequest createFlightRequest) {

        if (createFlightRequest.getArrivalTime().isBefore(createFlightRequest.getDepartureTime())) {
            throw new BadRequestException("Arrival Time cannot be before the departure time");
        }

        if (flightRepo.existsByFlightNumber(createFlightRequest.getFlightNumber())) {
            throw new BadRequestException("Flight with this number already exists");
        }

        //fetch and validate the departure airport
        Airport departureAirport = airportRepo.findByIataCode(createFlightRequest.getDepartureAirportIataCode())
                .orElseThrow(() -> new NotFoundException("Departure Airport Not Found"));

        //fetch and validate the departure airport
        Airport arrivalAirport = airportRepo.findByIataCode(createFlightRequest.getArrivalAirportIataCode())
                .orElseThrow(() -> new NotFoundException("Arrival Airport Not Found"));


        Flight flightToSave = new Flight();
        flightToSave.setFlightNumber(createFlightRequest.getFlightNumber());
        flightToSave.setDepartureAirport(departureAirport);
        flightToSave.setArrivalAirport(arrivalAirport);
        flightToSave.setDepartureTime(createFlightRequest.getDepartureTime());
        flightToSave.setArrivalTime(createFlightRequest.getArrivalTime());
        flightToSave.setBasePrice(createFlightRequest.getBaseprice());
        flightToSave.setStatus(FlightStatus.SCHEDUALED);

        //assign pilot to the flight(get and validate the pilot)
        if (createFlightRequest.getPilotID() != null){

            User pilot = userRepo.findById(createFlightRequest.getPilotID())
                    .orElseThrow(()-> new NotFoundException("Pilot is not found"));

            boolean isPilot = pilot.getRoles().stream()
                    .anyMatch(role -> role.getName().equalsIgnoreCase("PILOT"));

            if (!isPilot){
                throw new BadRequestException("Claimed User-Pilot not a certified pilot");
            }
            flightToSave.setAssignedPilot(pilot);
        }

        //save the flight
        flightRepo.save(flightToSave);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("FLight saved successfully")
                .build();

    }

    @Override
    public Response<FlightDTO> getFlightById(Long id) {

        Flight flight = flightRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("FLight Not Found"));

        FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);

        if (flightDTO.getBookings() != null){
            flightDTO.getBookings().forEach(bookingDTO -> bookingDTO.setFlight(null));
        }

        return Response.<FlightDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Flight retrieved Successfully")
                .data(flightDTO)
                .build();

    }

    @Override
    public Response<List<FlightDTO>> getAllFlights() {
       Sort sortByIdDesc = Sort.by(Sort.Direction.DESC, "id") ;
       List<FlightDTO> flights = flightRepo.findAll(sortByIdDesc).stream()
               .map(flight -> {
                   FlightDTO flightDTO = modelMapper.map(flight , FlightDTO.class);
                   if (flightDTO.getBookings() != null){
                       flightDTO
                               .getBookings().forEach(bookingDTO -> bookingDTO.setFlight(null));

                   }
                   return  flightDTO ;
               }).toList();
        return Response.<List<FlightDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(flights.isEmpty() ?"no Flight Found" : "Flights Found Succesfully")
                .data(flights)
                .build() ;

    }

    @Override
    @Transactional
    public Response<?> updateFlight(CreateFlightRequest flightRequest) {
        Long id = flightRequest.getId();
        Flight existingFlight = flightRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Flight Not Found"));

        if (flightRequest.getDepartureTime() != null){
            existingFlight.setDepartureTime(flightRequest.getDepartureTime());
        }

        if (flightRequest.getArrivalTime() != null){
            existingFlight.setArrivalTime(flightRequest.getArrivalTime());
        }

        if (flightRequest.getBaseprice() != null){
            existingFlight.setBasePrice(flightRequest.getBaseprice());
        }

        if (flightRequest.getStatus() != null){
            existingFlight.setStatus(flightRequest.getStatus());
        }
        if (existingFlight.getArrivalTime().isBefore(existingFlight.getDepartureTime())) {
            throw new BadRequestException("Arrival Time cannot be before the departure time");
        }

        //if pilot id is passed in validate the pilot and update it

        if (flightRequest.getPilotID() != null){

            User pilot = userRepo.findById(flightRequest.getPilotID())
                    .orElseThrow(()-> new NotFoundException("Pilot is not found"));

            boolean isPilot = pilot.getRoles().stream()
                    .anyMatch(role -> role.getName().equalsIgnoreCase("PILOT"));

            if (!isPilot){
                throw new BadRequestException("Claimed User-Pilot not a certified pilot");
            }
            existingFlight.setAssignedPilot(pilot);
        }

        flightRepo.save(existingFlight);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("FLight Updated Successfully")
                .build();

    }


    @Override
    public Response<List<FlightDTO>> searchFlight(String departurePortIata, String arrivalPortIata, FlightStatus status, LocalDate departureDate) {

        // Define the start and end of the day for the given departureDate
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.plusDays(1).atStartOfDay().minusNanos(1); // End of day (23:59:59.999...)

        List<Flight> flights = flightRepo.findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndStatusAndDepartureTimeBetween(
                departurePortIata, arrivalPortIata, status, startOfDay, endOfDay
        );

        List<FlightDTO> flightDTOS = flights.stream()
                .map(flight -> {
                    FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
                    flightDTO.setAssignedPilot(null);
                    flightDTO.setBookings(null);
                    return flightDTO;
                }).toList();


        return Response.<List<FlightDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(flightDTOS.isEmpty() ? "No FLights Found": "FLight retreived sucessfully")
                .data(flightDTOS)
                .build();
    }

    @Override
    public Response<List<City>> getAllCities() {
        return Response.<List<City>>builder()
                .statusCode(HttpStatus.OK.value()).message("Success").data(List.of(City.values())).build();
    }

    @Override
    public Response<List<Country>> getAllCountry() {
        return Response.<List<Country>>builder()
                .statusCode(HttpStatus.OK.value()).message("Success").data(List.of(Country.values())).build();
    }
}
