package mrs.eclinicapi.model; /***********************************************************************
 * Module:  ClinicRoom.java
 * Author:  Manojlović
 * Purpose: Defines the Class ClinicRoom
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ClinicRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Clinic clinic;

}
