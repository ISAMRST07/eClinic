package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Nurse.java
 * Author:  Manojlović
 * Purpose: Defines the Class Nurse
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
@NoArgsConstructor
@Entity
public class Nurse extends MedicalStaff {

    private String nursePosition;

}
