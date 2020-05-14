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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Visit {
    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "v_seq")
    @GenericGenerator(name = "v_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "V")})
    private String id;

    private String anamnesis;

    @ManyToOne
    private MedicalRecord medicalRecord;

    @OneToOne
    private Intervention intervention;

    @ManyToMany
    private List<Diagnosis> diagnoses;

    @ManyToMany
    private List<Prescription> prescriptions;

}
