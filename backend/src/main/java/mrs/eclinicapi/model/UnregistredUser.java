package mrs.eclinicapi.model; /***********************************************************************
 * Module:  UnregistredUser.java
 * Author:  Manojlović
 * Purpose: Defines the Class UnregistredUser
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
public class UnregistredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    private ClinicalCentarAdministrator admin;

}
