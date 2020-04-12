package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Medicine.java
 * Author:  Manojlović
 * Purpose: Defines the Class Medicine
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*TODO
    @ManyToMany(mappedBy = "medicine")
    private List<Codebook> codebooks;*/
    private int name;

}
