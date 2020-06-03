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
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class Intervention {


    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "i_seq")
    @GenericGenerator(name = "i_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "I")})
    private String id;

    @Embedded
    TimePeriod<LocalDateTime> dateTime = new TimePeriod<>();

    @ManyToOne
    private ClinicRoom clinicRoom;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Clinic clinic;

    @ManyToOne
    private InterventionType interventionType;

    @OneToOne
    private Visit visit;

    private double price;

    public Intervention(AppointmentRequest ar, ClinicRoom cr) {
        this.setPatient(ar.getPatient());
        this.setDoctor(ar.getDoctor());
        this.setClinic(ar.getClinic());
        this.setClinicRoom(cr);
        this.setInterventionType(ar.getInterventionType());
        this.dateTime.setStart(ar.getDateTime());
        this.dateTime.setEnd(ar.getDateTime().plusMinutes(30));
    }

    public Intervention(OneClickAppointment ap, Patient p) {
        this.setPatient(p);
        this.setDoctor(ap.getDoctor());
        this.setDateTime(ap.getDateTime());
        this.setInterventionType(ap.getInterventionType());
        this.setClinicRoom(ap.getClinicRoom());
        this.setClinic(ap.getClinic());
    }

    @Override
    public String toString() {
        return "Intervention [id=" + id + ", dateTime=" + dateTime + ", interventionType=" + interventionType
                + ", price=" + price + "]";
    }

}
