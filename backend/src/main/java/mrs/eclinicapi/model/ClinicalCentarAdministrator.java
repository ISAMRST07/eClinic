package mrs.eclinicapi.model; /***********************************************************************
 * Module:  ClinicalCentarAdministrator.java
 * Author:  Manojlović
 * Purpose: Defines the Class ClinicalCentarAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid 7d475349-9842-43c8-853f-89494e57dad3 */
public class ClinicalCentarAdministrator extends User {
   /** @pdRoleInfo migr=no name=ClinicAdministrator assc=association13 coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public java.util.Collection<ClinicAdministrator> clinicAdministrator;
   /** @pdRoleInfo migr=no name=UnregistredUser assc=association14 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<UnregistredUser> unregistredUser;
   /** @pdRoleInfo migr=no name=Codebook assc=association15 mult=1..1 */
   public Codebook codebook;


   /** @pdGenerated default getter */
   public java.util.Collection<ClinicAdministrator> getClinicAdministrator() {
      if (clinicAdministrator == null)
         clinicAdministrator = new java.util.HashSet<ClinicAdministrator>();
      return clinicAdministrator;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorClinicAdministrator() {
      if (clinicAdministrator == null)
         clinicAdministrator = new java.util.HashSet<ClinicAdministrator>();
      return clinicAdministrator.iterator();
   }

   /** @pdGenerated default setter
     * @param newClinicAdministrator */
   public void setClinicAdministrator(java.util.Collection<ClinicAdministrator> newClinicAdministrator) {
      removeAllClinicAdministrator();
      for (java.util.Iterator iter = newClinicAdministrator.iterator(); iter.hasNext();)
         addClinicAdministrator((ClinicAdministrator)iter.next());
   }

   /** @pdGenerated default add
     * @param newClinicAdministrator */
   public void addClinicAdministrator(ClinicAdministrator newClinicAdministrator) {
      if (newClinicAdministrator == null)
         return;
      if (this.clinicAdministrator == null)
         this.clinicAdministrator = new java.util.HashSet<ClinicAdministrator>();
      if (!this.clinicAdministrator.contains(newClinicAdministrator))
         this.clinicAdministrator.add(newClinicAdministrator);
   }

   /** @pdGenerated default remove
     * @param oldClinicAdministrator */
   public void removeClinicAdministrator(ClinicAdministrator oldClinicAdministrator) {
      if (oldClinicAdministrator == null)
         return;
      if (this.clinicAdministrator != null)
         if (this.clinicAdministrator.contains(oldClinicAdministrator))
            this.clinicAdministrator.remove(oldClinicAdministrator);
   }

   /** @pdGenerated default removeAll */
   public void removeAllClinicAdministrator() {
      if (clinicAdministrator != null)
         clinicAdministrator.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<UnregistredUser> getUnregistredUser() {
      if (unregistredUser == null)
         unregistredUser = new java.util.HashSet<UnregistredUser>();
      return unregistredUser;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorUnregistredUser() {
      if (unregistredUser == null)
         unregistredUser = new java.util.HashSet<UnregistredUser>();
      return unregistredUser.iterator();
   }

   /** @pdGenerated default setter
     * @param newUnregistredUser */
   public void setUnregistredUser(java.util.Collection<UnregistredUser> newUnregistredUser) {
      removeAllUnregistredUser();
      for (java.util.Iterator iter = newUnregistredUser.iterator(); iter.hasNext();)
         addUnregistredUser((UnregistredUser)iter.next());
   }

   /** @pdGenerated default add
     * @param newUnregistredUser */
   public void addUnregistredUser(UnregistredUser newUnregistredUser) {
      if (newUnregistredUser == null)
         return;
      if (this.unregistredUser == null)
         this.unregistredUser = new java.util.HashSet<UnregistredUser>();
      if (!this.unregistredUser.contains(newUnregistredUser))
         this.unregistredUser.add(newUnregistredUser);
   }

   /** @pdGenerated default remove
     * @param oldUnregistredUser */
   public void removeUnregistredUser(UnregistredUser oldUnregistredUser) {
      if (oldUnregistredUser == null)
         return;
      if (this.unregistredUser != null)
         if (this.unregistredUser.contains(oldUnregistredUser))
            this.unregistredUser.remove(oldUnregistredUser);
   }

   /** @pdGenerated default removeAll */
   public void removeAllUnregistredUser() {
      if (unregistredUser != null)
         unregistredUser.clear();
   }

}
