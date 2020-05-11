package mrs.eclinicapi.DTO;

import mrs.eclinicapi.model.Doctor;

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
	String date;
    String time;
    ClinicRoom selectedClinicRoom;    
    Doctor selectedDoctor;
    InterventionType selectedInterventionType; 
    double duration;
    double price;
    
	@Override
	public String toString() {
		return "InterventionDTO [id=" + id + ", date=" + date + ", time=" + time + ", duration=" + duration + ", price="
				+ price + "]";
	}
	
}
