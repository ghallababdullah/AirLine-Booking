package come.SocatraAirline.SocatraAirline.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import come.SocatraAirline.SocatraAirline.enums.AuthMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;

    private String name;

    private String email;

    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;

    private boolean emailVerified;


    private AuthMethod provider;

    private String providerId;


    private List<RoleDTO> roles;

    private boolean active;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAT;


}
