package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Codebook.java
 * Author:  Manojlović
 * Purpose: Defines the Class Codebook
 ***********************************************************************/

import java.util.*;

/** @pdOid ad9b3a86-e4b4-4f70-9244-189e839fa501 */
public class Codebook {
   /** @pdRoleInfo migr=no name=Medicine assc=association16 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Medicine> medicine;


   /** @pdGenerated default getter */
   public java.util.Collection<Medicine> getMedicine() {
      if (medicine == null)
         medicine = new java.util.HashSet<Medicine>();
      return medicine;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicine() {
      if (medicine == null)
         medicine = new java.util.HashSet<Medicine>();
      return medicine.iterator();
   }

   /** @pdGenerated default setter
     * @param newMedicine */
   public void setMedicine(java.util.Collection<Medicine> newMedicine) {
      removeAllMedicine();
      for (java.util.Iterator iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((Medicine)iter.next());
   }

   /** @pdGenerated default add
     * @param newMedicine */
   public void addMedicine(Medicine newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicine == null)
         this.medicine = new java.util.HashSet<Medicine>();
      if (!this.medicine.contains(newMedicine))
         this.medicine.add(newMedicine);
   }

   /** @pdGenerated default remove
     * @param oldMedicine */
   public void removeMedicine(Medicine oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicine != null)
         if (this.medicine.contains(oldMedicine))
            this.medicine.remove(oldMedicine);
   }

   /** @pdGenerated default removeAll */
   public void removeAllMedicine() {
      if (medicine != null)
         medicine.clear();
   }

}
