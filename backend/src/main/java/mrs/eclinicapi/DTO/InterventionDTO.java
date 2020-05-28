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
import mrs.eclinicapi.model.TimePeriod;
import org.apache.tomcat.jni.Local;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class InterventionDTO {

	String id;
	TimePeriod<LocalDateTime> dateTime;
	String clinicRoomID;
	String doctorID;
	String interventionTypeID;
	String patientID;
	String clinicID;
	double price;

	@Override
	public String toString() {
		return "InterventionDTO";
	}

}
