package come.SocatraAirline.SocatraAirline.services.imple;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.RoleDTO;
import come.SocatraAirline.SocatraAirline.entities.Role;
import come.SocatraAirline.SocatraAirline.exceptions.NotFoundException;
import come.SocatraAirline.SocatraAirline.repo.RoleRepo;
import come.SocatraAirline.SocatraAirline.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo ;
    private final ModelMapper modelMapper ;
    @Override
    public Response<?> createRole(RoleDTO roleDTO) {
        log.info("Inside createRole()");
        Role role = modelMapper.map(roleDTO , Role.class);

        role.setName(role.getName().toUpperCase());
        roleRepo.save(role);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public Response<?> updateRole(RoleDTO roleDTO) {

        log.info("Inside updateRole()");
        Long id = roleDTO.getId();
        Role existingRole = roleRepo.findById(id).orElseThrow(()-> new NotFoundException("Role not Found !"));


        existingRole.setName(roleDTO.getName().toUpperCase());
        roleRepo.save(existingRole);
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role updated Successfully !")
                .build();
    }

    @Override
    public Response<List<RoleDTO>> getAllRoles() {
        log.info("Inside getAllRoles()");
        List<RoleDTO > roles = roleRepo.findAll().stream().map(role -> modelMapper.map(role, RoleDTO.class)
                ).toList() ;
        return Response.<List<RoleDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(roles.isEmpty() ? "No Roles are found" :"Roles Retreived uccessfully")
                .data(roles)
                .build();

    }



    }

