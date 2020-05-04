package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Codebook {

    @Id
@Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cb_seq")
    @GenericGenerator(name = "cb_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "CB")})
    private String id;

    @ManyToOne
    private ClinicalCentarAdministrator admin;

    @OneToMany(mappedBy = "codebook")
    private List<Medicine> medicine;

    public Collection<Medicine> getMedicine() {
        if (medicine == null)
            medicine = new ArrayList<>();
        return medicine;
    }

    public java.util.Iterator<Medicine> getIteratorMedicine() {
        if (medicine == null)
            medicine = new ArrayList<>();
        return medicine.iterator();
    }

    public void setMedicine(java.util.Collection<Medicine> newMedicine) {
        removeAllMedicine();
        for (Medicine value : newMedicine) addMedicine(value);
    }

    public void addMedicine(Medicine newMedicine) {
        if (newMedicine == null)
            return;
        if (this.medicine == null)
            this.medicine = new ArrayList<>();
        if (!this.medicine.contains(newMedicine))
            this.medicine.add(newMedicine);
    }

    public void removeMedicine(Medicine oldMedicine) {
        if (oldMedicine == null)
            return;
        if (this.medicine != null)
            this.medicine.remove(oldMedicine);
    }

    public void removeAllMedicine() {
        if (medicine != null)
            medicine.clear();
    }

}
