package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Nurse extends MedicalStaff {

    private String position;

    @JsonIgnoreProperties("nurses")
    @Override
    public Clinic getClinic() {
        return super.getClinic();
    }
}
