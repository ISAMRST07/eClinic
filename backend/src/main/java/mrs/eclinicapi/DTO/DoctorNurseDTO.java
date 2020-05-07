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
public class DoctorNurseDTO {

	String id;
	String email;
	String name;
	String surname;
	String phone;
	String address;
	String city;
	String country;
	String jmbg;
	String position;

	@Override
	public String toString() {
		return "DoctorDTO [id=" + id + " email=" + email + ", name=" + name + ", surname=" + surname + ", phone=" + phone
			    + ", address=" + address + ", city=" + city + ", country=" + country
				+ ", jmbg=" + jmbg + ", position=" + position + "]";
	}

}
