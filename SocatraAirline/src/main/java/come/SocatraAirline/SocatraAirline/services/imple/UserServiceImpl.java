package come.SocatraAirline.SocatraAirline.services.imple;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.UserDTO;
import come.SocatraAirline.SocatraAirline.entities.User;
import come.SocatraAirline.SocatraAirline.exceptions.NotFoundException;
import come.SocatraAirline.SocatraAirline.repo.UserRepo;
import come.SocatraAirline.SocatraAirline.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper  modelMapper ;


    @Override
    public User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName() ;
        return userRepo.findByEmail(email)
                .orElseThrow(()->new NotFoundException("User Not Found !")) ;
    }

    @Override
    @Transactional
    public Response<?> updateMyAccount(UserDTO userDTO) {
        log.info("inside updateMyAccount() ");
        User user= currentUser() ;
        log.info(String.valueOf(userDTO));
        if (userDTO.getName()!= null && !userDTO.getName().isBlank()){
            user.setName(userDTO.getName());
        }
        if (userDTO.getPhoneNumber()!= null && !userDTO.getPhoneNumber().isBlank()){
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getPassword()!= null && !userDTO.getPassword().isBlank()){
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(encodedPassword);
        }
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        User saveduser = userRepo.save(user);
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Account Updated Succesfully")
                .data(saveduser)
                .build() ;



    }

    @Override
    public Response<List<UserDTO>> getAllPilot() {
        log.info("inside getAllPilot() ");
        List<UserDTO> pilots = userRepo.findByRoleName("PILOT").stream().map(user ->modelMapper.map(user, UserDTO.class))
                .toList();
        return Response.<List<UserDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(pilots.isEmpty()?"No Pilots found " : "Pilot rettieved successfully ")
                .data(pilots)
                .build();

    }

    @Override
    public Response<UserDTO> getAccountDetails() {
        log.info(" inside getAcountDetails");
        User user = currentUser() ;
        UserDTO userDTO = modelMapper.map(user ,UserDTO.class );
        return Response.<UserDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Succes")
                .data(userDTO)
                .build() ;

    }

}
