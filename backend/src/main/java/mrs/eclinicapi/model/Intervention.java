package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Intervention.java
 * Author:  Manojlović
 * Purpose: Defines the Class Intervention
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.enums.InterventionType;

import javax.persistence.*;


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

    @ManyToOne
    private Patient patient;

}
