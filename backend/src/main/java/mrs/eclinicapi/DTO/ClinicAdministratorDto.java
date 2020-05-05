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
    private String id;
    private String password;
    private String surname;
    private String name;
    private UserType type;
    private Clinic clinic;
    private String email;
    private String userID;
    private String city;
    private String country;
    private String address;
    private String personalID; //JMBG

    public ClinicAdministratorDto(ClinicAdministrator clinicAdministrator, int i) {
        this.id = clinicAdministrator.getId();
        this.password = clinicAdministrator.getUser().getPassword();
        this.surname = clinicAdministrator.getUser().getSurname();
        this.name = clinicAdministrator.getUser().getName();
        this.type = clinicAdministrator.getUser().getType();
        this.clinic = clinicAdministrator.getClinic();
        this.email = clinicAdministrator.getUser().getEmail();;
        this.city = clinicAdministrator.getUser().getCity();;
        this.country = clinicAdministrator.getUser().getCountry();
        this.userID = clinicAdministrator.getUser().getId();
        this.address = clinicAdministrator.getUser().getAddress();
        this.personalID = clinicAdministrator.getUser().getPersonalID(); //JMBG
    }
}
