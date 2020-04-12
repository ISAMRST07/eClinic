package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Prescription.java
 * Author:  Manojlović
 * Purpose: Defines the Class Prescription
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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne
   public Medicine medicine;

}
