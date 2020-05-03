package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*ovde nije CascadeType.ALL da ne bi kada obrises doctor/nurse bili i
    user i clinic obrisani iz svojih tabela*/
    //@OneToOne(cascade = {CascadeType.PERSIST})
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Clinic clinic;

	@Override
	public String toString() {
		return "MedicalStaff [id=" + id + ", user=" + user + ", clinic=" + clinic + "]";
	}
}
