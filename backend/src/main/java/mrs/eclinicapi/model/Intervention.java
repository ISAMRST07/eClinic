package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class Intervention {

    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "i_seq")
    @GenericGenerator(name = "i_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "I")})
    private String id;

    @Embedded
    TimePeriod<LocalDateTime> dateTime;

    @ManyToOne
    private ClinicRoom clinicRoom;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Clinic clinic;

    @OneToOne
    private InterventionType interventionType;

    @OneToOne
    private Visit visit;

    private double price;

	@Override
	public String toString() {
		return "Intervention [id=" + id + ", dateTime=" + dateTime + ", interventionType=" + interventionType
				+ ", price=" + price + "]";
	}

}
