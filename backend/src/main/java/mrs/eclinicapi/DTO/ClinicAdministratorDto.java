package mrs.eclinicapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.enums.UserType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ClinicAdministratorDto {
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private UserType type;
    private Clinic clinic;

    public ClinicAdministratorDto(ClinicAdministrator clinicAdministrator) {
        this.id = clinicAdministrator.getId();
        this.username = clinicAdministrator.getUser().getUsername();
        this.password = clinicAdministrator.getUser().getPassword();
        this.surname = clinicAdministrator.getUser().getSurname();
        this.name = clinicAdministrator.getUser().getName();
        this.type = clinicAdministrator.getUser().getType();
        this.clinic = clinicAdministrator.getClinic();

    }

    public ClinicAdministratorDto(ClinicAdministrator clinicAdministrator, int i) {
        this.id = clinicAdministrator.getId();
        this.username = clinicAdministrator.getUser().getUsername();
        this.password = clinicAdministrator.getUser().getPassword();
        this.surname = clinicAdministrator.getUser().getSurname();
        this.name = clinicAdministrator.getUser().getName();
        this.type = clinicAdministrator.getUser().getType();
        this.clinic = clinicAdministrator.getClinic();
        this.clinic.setClinicAdministrator(null);
    }
}
