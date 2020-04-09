package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Nurse.java
 * Author:  Manojlović
 * Purpose: Defines the Class Nurse
 ***********************************************************************/

import java.util.*;

/** @pdOid 961f20d2-0535-4f66-9c6c-2439d469fe31 */
public class Nurse extends MedicalStaff {
   /** @pdRoleInfo migr=no name=Prescription assc=association21 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Prescription> prescription;


   /** @pdGenerated default getter */
   public java.util.Collection<Prescription> getPrescription() {
      if (prescription == null)
         prescription = new java.util.HashSet<Prescription>();
      return prescription;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPrescription() {
      if (prescription == null)
         prescription = new java.util.HashSet<Prescription>();
      return prescription.iterator();
   }

   /** @pdGenerated default setter
     * @param newPrescription */
   public void setPrescription(java.util.Collection<Prescription> newPrescription) {
      removeAllPrescription();
      for (java.util.Iterator iter = newPrescription.iterator(); iter.hasNext();)
         addPrescription((Prescription)iter.next());
   }

   /** @pdGenerated default add
     * @param newPrescription */
   public void addPrescription(Prescription newPrescription) {
      if (newPrescription == null)
         return;
      if (this.prescription == null)
         this.prescription = new java.util.HashSet<Prescription>();
      if (!this.prescription.contains(newPrescription))
         this.prescription.add(newPrescription);
   }

   /** @pdGenerated default remove
     * @param oldPrescription */
   public void removePrescription(Prescription oldPrescription) {
      if (oldPrescription == null)
         return;
      if (this.prescription != null)
         if (this.prescription.contains(oldPrescription))
            this.prescription.remove(oldPrescription);
   }

   /** @pdGenerated default removeAll */
   public void removeAllPrescription() {
      if (prescription != null)
         prescription.clear();
   }

}
