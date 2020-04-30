package mrs.eclinicapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.enums.InterventionType;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InterventionType type;
    private LocalDateTime dateTime;

    @ManyToOne
    private ClinicRoom clinicRoom;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

}
