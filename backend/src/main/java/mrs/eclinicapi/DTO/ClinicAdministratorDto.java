package mrs.eclinicapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.enums.UserType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicAdministratorDto {
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private UserType type;

    public ClinicAdministratorDto(ClinicAdministrator clinicAdministrator) {
        this.id = clinicAdministrator.getId();
        this.username = clinicAdministrator.getUser().getUsername();
        this.password = clinicAdministrator.getUser().getPassword();
        this.surname = clinicAdministrator.getUser().getSurname();
        this.name = clinicAdministrator.getUser().getName();
        this.type = clinicAdministrator.getUser().getType();

    }
}
