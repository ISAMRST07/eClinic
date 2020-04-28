package mrs.eclinicapi.model; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String address;

    @Embedded
    private Coordinates coordinates;

    @OneToOne
    private ClinicAdministrator clinicAdministrator;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClinicRoom> clinicRoom = new HashSet<ClinicRoom>();
    
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Doctor> doctors = new HashSet<Doctor>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Nurse> nurses = new HashSet<Nurse>();;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private List<Patient> patients;
    
    @ManyToOne
    private ClinicalCentarAdministrator admin;
    
    @Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", clinicAdministrator=" + clinicAdministrator + ", clinicRoom="
				+ clinicRoom + ", doctors=" + doctors + ", nurses=" + nurses + ", patients=" + patients + ", admin="
				+ admin + "]";
	}
}
