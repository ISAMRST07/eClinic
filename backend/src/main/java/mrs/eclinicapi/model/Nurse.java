package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Nurse.java
 * Author:  Manojlović
 * Purpose: Defines the Class Nurse
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nurse extends MedicalStaff {



    private String nursePosition;
//  TODO

 //  private List<Prescription> prescription;
//
//   public Collection<Prescription> getPrescription() {
//      if (prescription == null)
//         prescription = new ArrayList<>();
//      return prescription;
//   }
//      CTRL + /
//   public java.util.Iterator getIteratorPrescription() {
//      if (prescription == null)
//         prescription = new ArrayList<>();
//      return prescription.iterator();
//   }
//
//   public void setPrescription(java.util.Collection<Prescription> newPrescription) {
//      removeAllPrescription();
//      for (java.util.Iterator iter = newPrescription.iterator(); iter.hasNext();)
//         addPrescription((Prescription)iter.next());
//   }
//
//   public void addPrescription(Prescription newPrescription) {
//      if (newPrescription == null)
//         return;
//      if (this.prescription == null)
//         this.prescription = new ArrayList<>();
//      if (!this.prescription.contains(newPrescription))
//         this.prescription.add(newPrescription);
//   }
//
//   public void removePrescription(Prescription oldPrescription) {
//      if (oldPrescription == null)
//         return;
//      if (this.prescription != null)
//         if (this.prescription.contains(oldPrescription))
//            this.prescription.remove(oldPrescription);
//   }
//
//   public void removeAllPrescription() {
//      if (prescription != null)
//         prescription.clear();
//   }

}
