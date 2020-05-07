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

public class DoctorDTO {

	String name;
	String surname;
	String username;
	String password;
	String position;

	@Override
	public String toString() {
		return "DoctorDTO [name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password
				+ ", position=" + position + "]";
	}

}
