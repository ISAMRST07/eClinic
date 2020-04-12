package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Doctor.java
 * Author:  Manojlović
 * Purpose: Defines the Class Doctor
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
@Entity
@NoArgsConstructor
public class Doctor extends MedicalStaff {

    private String position;


   //public List<Intervention> intervention;
/*TODO
   public Collection<Intervention> getIntervention() {
      if (intervention == null)
         intervention = new ArrayList<>();
      return intervention;
   }

   public java.util.Iterator getIteratorIntervention() {
      if (intervention == null)
         intervention = new ArrayList<>();
      return intervention.iterator();
   }

   public void setIntervention(java.util.Collection<Intervention> newIntervention) {
      removeAllIntervention();
      for (java.util.Iterator iter = newIntervention.iterator(); iter.hasNext();)
         addIntervention((Intervention)iter.next());
   }

   public void addIntervention(Intervention newIntervention) {
      if (newIntervention == null)
         return;
      if (this.intervention == null)
         this.intervention = new ArrayList<>();
      if (!this.intervention.contains(newIntervention))
         this.intervention.add(newIntervention);
   }

   public void removeIntervention(Intervention oldIntervention) {
      if (oldIntervention == null)
         return;
      if (this.intervention != null)
         if (this.intervention.contains(oldIntervention))
            this.intervention.remove(oldIntervention);
   }

   public void removeAllIntervention() {
      if (intervention != null)
         intervention.clear();
   }*/

}
