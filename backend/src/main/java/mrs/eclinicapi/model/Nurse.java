package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class Nurse extends MedicalStaff {

    private String position;


    //    @JsonIgnoreProperties("nurses")
    @Override
    public Clinic getClinic() {
        return super.getClinic();
    }


    @Override
    public String toString() {
        return "Nurse [position=" + position + "]";
    }
}
