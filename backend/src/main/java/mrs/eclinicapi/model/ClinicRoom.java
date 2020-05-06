package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ClinicRoom {

    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cr_seq")
    @GenericGenerator(name = "cr_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "CR")})
    private String id;

    private String name;

    @OneToMany(mappedBy = "clinicRoom", fetch = FetchType.LAZY)
    private Set<Intervention> interventions = new HashSet<>();

    @JsonIgnoreProperties("clinicRoom")
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Clinic clinic;


}
