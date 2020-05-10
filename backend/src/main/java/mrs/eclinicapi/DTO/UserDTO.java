package mrs.eclinicapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.enums.UserType;

@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class UserDTO {
    protected String id;
    protected String password;
    protected String surname;
    protected String name;
    protected UserType type;
    protected Clinic clinic;
    protected String email;
    protected String userID;
    protected String city;
    protected String country;
    protected String address;
    protected String personalID;

    public UserDTO() {
    }
}