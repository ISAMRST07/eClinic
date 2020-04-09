package mrs.eclinicapi.model; /***********************************************************************
 * Module:  MedicalRecord.java
 * Author:  Manojlović
 * Purpose: Defines the Class MedicalRecord
 ***********************************************************************/

import java.util.*;

/** @pdOid 390483d6-8440-49da-8f26-3aa3b1cbab0d */
public class MedicalRecord {
   /** @pdRoleInfo migr=no name=Examination assc=association2 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Examination> examination;
   /** @pdRoleInfo migr=no name=Disease assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Disease> disease;
   /** @pdRoleInfo migr=no name=Prescription assc=association19 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Prescription> prescription;


   /** @pdGenerated default getter */
   public java.util.Collection<Examination> getExamination() {
      if (examination == null)
         examination = new java.util.HashSet<Examination>();
      return examination;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorExamination() {
      if (examination == null)
         examination = new java.util.HashSet<Examination>();
      return examination.iterator();
   }

   /** @pdGenerated default setter
     * @param newExamination */
   public void setExamination(java.util.Collection<Examination> newExamination) {
      removeAllExamination();
      for (java.util.Iterator iter = newExamination.iterator(); iter.hasNext();)
         addExamination((Examination)iter.next());
   }

   /** @pdGenerated default add
     * @param newExamination */
   public void addExamination(Examination newExamination) {
      if (newExamination == null)
         return;
      if (this.examination == null)
         this.examination = new java.util.HashSet<Examination>();
      if (!this.examination.contains(newExamination))
         this.examination.add(newExamination);
   }

   /** @pdGenerated default remove
     * @param oldExamination */
   public void removeExamination(Examination oldExamination) {
      if (oldExamination == null)
         return;
      if (this.examination != null)
         if (this.examination.contains(oldExamination))
            this.examination.remove(oldExamination);
   }

   /** @pdGenerated default removeAll */
   public void removeAllExamination() {
      if (examination != null)
         examination.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Disease> getDisease() {
      if (disease == null)
         disease = new java.util.HashSet<Disease>();
      return disease;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDisease() {
      if (disease == null)
         disease = new java.util.HashSet<Disease>();
      return disease.iterator();
   }

   /** @pdGenerated default setter
     * @param newDisease */
   public void setDisease(java.util.Collection<Disease> newDisease) {
      removeAllDisease();
      for (java.util.Iterator iter = newDisease.iterator(); iter.hasNext();)
         addDisease((Disease)iter.next());
   }

   /** @pdGenerated default add
     * @param newDisease */
   public void addDisease(Disease newDisease) {
      if (newDisease == null)
         return;
      if (this.disease == null)
         this.disease = new java.util.HashSet<Disease>();
      if (!this.disease.contains(newDisease))
         this.disease.add(newDisease);
   }

   /** @pdGenerated default remove
     * @param oldDisease */
   public void removeDisease(Disease oldDisease) {
      if (oldDisease == null)
         return;
      if (this.disease != null)
         if (this.disease.contains(oldDisease))
            this.disease.remove(oldDisease);
   }

   /** @pdGenerated default removeAll */
   public void removeAllDisease() {
      if (disease != null)
         disease.clear();
   }
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
