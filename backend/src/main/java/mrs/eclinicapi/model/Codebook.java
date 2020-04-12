package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Codebook.java
 * Author:  Manojlović
 * Purpose: Defines the Class Codebook
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
public class Codebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /*TODO
   @ManyToMany(mappedBy = "codebooks")
   public List<Medicine> medicine;

   public Collection<Medicine> getMedicine() {
      if (medicine == null)
         medicine = new ArrayList<>();
      return medicine;
   }

   public java.util.Iterator getIteratorMedicine() {
      if (medicine == null)
         medicine = new ArrayList<>();
      return medicine.iterator();
   }

   public void setMedicine(java.util.Collection<Medicine> newMedicine) {
      removeAllMedicine();
      for (java.util.Iterator iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((Medicine)iter.next());
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
         if (this.medicine.contains(oldMedicine))
            this.medicine.remove(oldMedicine);
   }

   public void removeAllMedicine() {
      if (medicine != null)
         medicine.clear();
   }*/

}
