package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Pacijent.java
 * Author:  Manojlović
 * Purpose: Defines the Class Pacijent
 ***********************************************************************/

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
    private ClinicAdministrator clinicAdministrator;

    @ManyToOne
    private ClinicAdministrator clinic;

    @OneToOne
    public MedicalRecord medicalRecord;


    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private  List<Intervention> intervention;

}
