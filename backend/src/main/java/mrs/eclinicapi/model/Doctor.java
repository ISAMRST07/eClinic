package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class Doctor extends MedicalStaff {

    private String position;

    @Override
    public String toString() {
        return "Doctor [position=" + position + "]";
    }

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Intervention> interventions = new ArrayList<>();

    //    @JsonIgnoreProperties("doctors")
    @Override
    public Clinic getClinic() {
        return super.getClinic();
    }
}
