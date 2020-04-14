package mrs.eclinicapi.model; /***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Manojlović
 * Purpose: Defines the Class ClinicAdministrator
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
public class ClinicAdministrator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;





    @OneToOne
    public Clinic clinic;


}
