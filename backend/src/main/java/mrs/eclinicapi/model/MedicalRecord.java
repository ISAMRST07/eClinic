package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)

public class MedicalRecord {
    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mr_seq")
    @GenericGenerator(name = "mr_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "MR")})
    private String id;
    @OneToOne
    private Patient patient;

   /*public List<Examination> examination;

   public List<Disease> disease;

   public List<Prescription> prescription;


   public Collection<Examination> getExamination() {
      if (examination == null)
         examination = new ArrayList<>();
      return examination;
   }

   public java.util.Iterator getIteratorExamination() {
      if (examination == null)
         examination = new ArrayList<>();
      return examination.iterator();
   }

   public void setExamination(java.util.Collection<Examination> newExamination) {
      removeAllExamination();
      for (java.util.Iterator iter = newExamination.iterator(); iter.hasNext();)
         addExamination((Examination)iter.next());
   }

   public void addExamination(Examination newExamination) {
      if (newExamination == null)
         return;
      if (this.examination == null)
         this.examination = new ArrayList<>();
      if (!this.examination.contains(newExamination))
         this.examination.add(newExamination);
   }

   public void removeExamination(Examination oldExamination) {
      if (oldExamination == null)
         return;
      if (this.examination != null)
         if (this.examination.contains(oldExamination))
            this.examination.remove(oldExamination);
   }

   public void removeAllExamination() {
      if (examination != null)
         examination.clear();
   }

   public Collection<Disease> getDisease() {
      if (disease == null)
         disease = new ArrayList<>();
      return disease;
   }

   public java.util.Iterator getIteratorDisease() {
      if (disease == null)
         disease = new ArrayList<>();
      return disease.iterator();
   }

   public void setDisease(java.util.Collection<Disease> newDisease) {
      removeAllDisease();
      for (java.util.Iterator iter = newDisease.iterator(); iter.hasNext();)
         addDisease((Disease)iter.next());
   }

   public void addDisease(Disease newDisease) {
      if (newDisease == null)
         return;
      if (this.disease == null)
         this.disease = new ArrayList<>();
      if (!this.disease.contains(newDisease))
         this.disease.add(newDisease);
   }

   public void removeDisease(Disease oldDisease) {
      if (oldDisease == null)
         return;
      if (this.disease != null)
         if (this.disease.contains(oldDisease))
            this.disease.remove(oldDisease);
   }

   public void removeAllDisease() {
      if (disease != null)
         disease.clear();
   }
   public Collection<Prescription> getPrescription() {
      if (prescription == null)
         prescription = new ArrayList<>();
      return prescription;
   }

   public java.util.Iterator getIteratorPrescription() {
      if (prescription == null)
         prescription = new ArrayList<>();
      return prescription.iterator();
   }

   public void setPrescription(java.util.Collection<Prescription> newPrescription) {
      removeAllPrescription();
      for (java.util.Iterator iter = newPrescription.iterator(); iter.hasNext();)
         addPrescription((Prescription)iter.next());
   }

   public void addPrescription(Prescription newPrescription) {
      if (newPrescription == null)
         return;
      if (this.prescription == null)
         this.prescription = new ArrayList<>();
      if (!this.prescription.contains(newPrescription))
         this.prescription.add(newPrescription);
   }

   public void removePrescription(Prescription oldPrescription) {
      if (oldPrescription == null)
         return;
      if (this.prescription != null)
         if (this.prescription.contains(oldPrescription))
            this.prescription.remove(oldPrescription);
   }

   public void removeAllPrescription() {
      if (prescription != null)
         prescription.clear();
   }*/

}
