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
public class MedicalRecord {
    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mr_seq")
    @GenericGenerator(name = "mr_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "MR")})
    private String id;
    @OneToOne
    private Patient patient;

    @ManyToMany
    private List<Diagnosis> diagnoses;

    @OneToMany
    private List<Visit> visits;

}
