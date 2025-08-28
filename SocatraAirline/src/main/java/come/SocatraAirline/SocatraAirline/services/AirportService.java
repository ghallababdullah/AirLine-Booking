package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.AirportDTO;
import come.SocatraAirline.SocatraAirline.dtos.Response;

import java.util.List;

public interface AirportService {
    Response <?> createAirport(AirportDTO airportDTO);

    Response <?> updateAirport(AirportDTO airportDTO);

    Response <List<AirportDTO>> getAllAirports();

    Response <AirportDTO> getAirportByID(Long id);


}
