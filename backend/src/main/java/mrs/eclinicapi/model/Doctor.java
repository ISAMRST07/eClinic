package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Doctor extends MedicalStaff {

    private String position;

    @Override
    public String toString() {
        return "Doctor [position=" + position + "]";
    }

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Intervention> interventions = new ArrayList<>();

    @JsonIgnoreProperties("doctors")
    @Override
    public Clinic getClinic() {
        return super.getClinic();
    }
}
