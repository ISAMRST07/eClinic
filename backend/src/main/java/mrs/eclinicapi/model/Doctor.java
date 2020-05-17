package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Doctor extends MedicalStaff {

    @ManyToMany
    private List<InterventionType> specialties;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Intervention> interventions = new ArrayList<>();

    //    @JsonIgnoreProperties("doctors")
    @Override
    public Clinic getClinic() {
        return super.getClinic();
    }

    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "d_seq")
    @GenericGenerator(name = "d_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "D")})
    private String id;

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                '}';
    }
}
