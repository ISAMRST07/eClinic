package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Doctor.java
 * Author:  Manojlović
 * Purpose: Defines the Class Doctor
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Doctor extends MedicalStaff {

    private String position;

	@Override
	public String toString() {
		return "Doctor [position=" + position + "]";
	}

    
}
