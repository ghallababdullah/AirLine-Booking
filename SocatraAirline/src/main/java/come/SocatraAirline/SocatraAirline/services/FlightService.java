package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.CreateFlightRequest;
import come.SocatraAirline.SocatraAirline.dtos.FlightDTO;
import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.enums.City;
import come.SocatraAirline.SocatraAirline.enums.Country;
import come.SocatraAirline.SocatraAirline.enums.FlightStatus;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Response<?> createFlight(CreateFlightRequest createFlightRequest) ;
    Response<FlightDTO> getFlightById(Long  id) ;
    Response<List<FlightDTO>> getAllFlights() ;
    Response<?> updateFlight(CreateFlightRequest createFlightRequest) ;
    Response<List<FlightDTO>> searchFlight(String departurePortIata , String arrivalPortIata , FlightStatus status , LocalDate departureDate) ;
    Response<List<City>> getAllCities();
    Response<List<Country>> getAllCountry();





}
