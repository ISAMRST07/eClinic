package mrs.eclinicapi.model; /***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Manojlović
 * Purpose: Defines the Class ClinicAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid 335bfd7c-27d7-4f1c-b72b-445fd7b9c706 */
public class ClinicAdministrator extends User {
   /** @pdRoleInfo migr=no name=Pacijent assc=association11 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Pacijent> pacijent;
   /** @pdRoleInfo migr=no name=MedicalStaff assc=association10 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<MedicalStaff> medicalStaff;
   /** @pdRoleInfo migr=no name=Clinic assc=association12 mult=1..1 */
   public Clinic clinic;


   /** @pdGenerated default getter */
   public java.util.Collection<Pacijent> getPacijent() {
      if (pacijent == null)
         pacijent = new java.util.HashSet<Pacijent>();
      return pacijent;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPacijent() {
      if (pacijent == null)
         pacijent = new java.util.HashSet<Pacijent>();
      return pacijent.iterator();
   }

   /** @pdGenerated default setter
     * @param newPacijent */
   public void setPacijent(java.util.Collection<Pacijent> newPacijent) {
      removeAllPacijent();
      for (java.util.Iterator iter = newPacijent.iterator(); iter.hasNext();)
         addPacijent((Pacijent)iter.next());
   }

   /** @pdGenerated default add
     * @param newPacijent */
   public void addPacijent(Pacijent newPacijent) {
      if (newPacijent == null)
         return;
      if (this.pacijent == null)
         this.pacijent = new java.util.HashSet<Pacijent>();
      if (!this.pacijent.contains(newPacijent))
         this.pacijent.add(newPacijent);
   }

   /** @pdGenerated default remove
     * @param oldPacijent */
   public void removePacijent(Pacijent oldPacijent) {
      if (oldPacijent == null)
         return;
      if (this.pacijent != null)
         if (this.pacijent.contains(oldPacijent))
            this.pacijent.remove(oldPacijent);
   }

   /** @pdGenerated default removeAll */
   public void removeAllPacijent() {
      if (pacijent != null)
         pacijent.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<MedicalStaff> getMedicalStaff() {
      if (medicalStaff == null)
         medicalStaff = new java.util.HashSet<MedicalStaff>();
      return medicalStaff;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicalStaff() {
      if (medicalStaff == null)
         medicalStaff = new java.util.HashSet<MedicalStaff>();
      return medicalStaff.iterator();
   }

   /** @pdGenerated default setter
     * @param newMedicalStaff */
   public void setMedicalStaff(java.util.Collection<MedicalStaff> newMedicalStaff) {
      removeAllMedicalStaff();
      for (java.util.Iterator iter = newMedicalStaff.iterator(); iter.hasNext();)
         addMedicalStaff((MedicalStaff)iter.next());
   }

   /** @pdGenerated default add
     * @param newMedicalStaff */
   public void addMedicalStaff(MedicalStaff newMedicalStaff) {
      if (newMedicalStaff == null)
         return;
      if (this.medicalStaff == null)
         this.medicalStaff = new java.util.HashSet<MedicalStaff>();
      if (!this.medicalStaff.contains(newMedicalStaff))
         this.medicalStaff.add(newMedicalStaff);
   }

   /** @pdGenerated default remove
     * @param oldMedicalStaff */
   public void removeMedicalStaff(MedicalStaff oldMedicalStaff) {
      if (oldMedicalStaff == null)
         return;
      if (this.medicalStaff != null)
         if (this.medicalStaff.contains(oldMedicalStaff))
            this.medicalStaff.remove(oldMedicalStaff);
   }

   /** @pdGenerated default removeAll */
   public void removeAllMedicalStaff() {
      if (medicalStaff != null)
         medicalStaff.clear();
   }

}
