package mrs.eclinicapi.model; /***********************************************************************
 * Module:  MedicalStaff.java
 * Author:  Manojlović
 * Purpose: Defines the Class MedicalStaff
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*ovde nije CascadeType.ALL da ne bi kada obrises doctor/nurse bili i 
    user i clinic obrisani iz svojih tabela*/
    @OneToOne(cascade = { CascadeType.PERSIST } )
    private User user;

    @JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
    private Clinic clinic;

    
}
