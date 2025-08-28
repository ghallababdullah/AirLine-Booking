package come.SocatraAirline.SocatraAirline.services;

import come.SocatraAirline.SocatraAirline.dtos.Response;
import come.SocatraAirline.SocatraAirline.dtos.RoleDTO;

import java.util.List;

public interface RoleService {
    Response<?> createRole (RoleDTO roleDTO);
    Response<?> updateRole (RoleDTO roleDTO);
    Response<List<RoleDTO> > getAllRoles ();

}
