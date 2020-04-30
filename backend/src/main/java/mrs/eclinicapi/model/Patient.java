package mrs.eclinicapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    private ClinicAdministrator clinic;

    @OneToOne
    public MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private  List<Intervention> intervention;

}
