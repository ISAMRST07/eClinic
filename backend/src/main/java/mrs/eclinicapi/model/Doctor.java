package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Doctor.java
 * Author:  Manojlović
 * Purpose: Defines the Class Doctor
 ***********************************************************************/

import java.util.*;

/** @pdOid dba5d8db-7f60-446d-8ee4-6ad7b5481a46 */
public class Doctor extends MedicalStaff {
   /** @pdRoleInfo migr=no name=Intervention assc=association18 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Intervention> intervention;


   /** @pdGenerated default getter */
   public java.util.Collection<Intervention> getIntervention() {
      if (intervention == null)
         intervention = new java.util.HashSet<Intervention>();
      return intervention;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorIntervention() {
      if (intervention == null)
         intervention = new java.util.HashSet<Intervention>();
      return intervention.iterator();
   }

   /** @pdGenerated default setter
     * @param newIntervention */
   public void setIntervention(java.util.Collection<Intervention> newIntervention) {
      removeAllIntervention();
      for (java.util.Iterator iter = newIntervention.iterator(); iter.hasNext();)
         addIntervention((Intervention)iter.next());
   }

   /** @pdGenerated default add
     * @param newIntervention */
   public void addIntervention(Intervention newIntervention) {
      if (newIntervention == null)
         return;
      if (this.intervention == null)
         this.intervention = new java.util.HashSet<Intervention>();
      if (!this.intervention.contains(newIntervention))
         this.intervention.add(newIntervention);
   }

   /** @pdGenerated default remove
     * @param oldIntervention */
   public void removeIntervention(Intervention oldIntervention) {
      if (oldIntervention == null)
         return;
      if (this.intervention != null)
         if (this.intervention.contains(oldIntervention))
            this.intervention.remove(oldIntervention);
   }

   /** @pdGenerated default removeAll */
   public void removeAllIntervention() {
      if (intervention != null)
         intervention.clear();
   }

}
