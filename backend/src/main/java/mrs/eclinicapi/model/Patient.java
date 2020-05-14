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
public class Patient {
    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pt_seq")
    @GenericGenerator(name = "pt_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "PT")})

    private String id;

    @ManyToMany
    private List<Clinic> clinic;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne
    public MedicalRecord medicalRecord;

}
