package mrs.eclinicapi.DTO;

import mrs.eclinicapi.model.Doctor;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.InterventionType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class InterventionDTO {

	String id;
	LocalDateTime dateTime;
	String selectedClinicRoom;    
	String selectedDoctor;
	String selectedInterventionType; 
	String clinic;
	double duration;
	double price;
    
	@Override
	public String toString() {
		return "InterventionDTO [id=" + id + ", dateTime=" + dateTime + ", selectedClinicRoom=" + selectedClinicRoom
				+ ", selectedDoctor=" + selectedDoctor + ", selectedInterventionType=" + selectedInterventionType
				+ ", clinic=" + clinic + ", duration=" + duration + ", price=" + price + "]";
	}
	
}
