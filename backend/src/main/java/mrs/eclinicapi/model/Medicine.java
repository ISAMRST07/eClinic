package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Medicine.java
 * Author:  Manojlović
 * Purpose: Defines the Class Medicine
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
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Codebook codebook;

    private int name;

}
