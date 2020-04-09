package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Clinic.java
 * Author:  Manojlović
 * Purpose: Defines the Class Clinic
 ***********************************************************************/

import java.util.*;

/** @pdOid a947e320-9ce8-4dd9-b68a-8662a8be71e5 */
public class Clinic {
   /** @pdOid 34c40215-3f40-48f8-a90b-951170226f7e */
   private String name;
   /** @pdOid 6ee524c1-c312-4378-8200-bacedf25d3d4 */
   private String id;

   /** @pdRoleInfo migr=no name=MedicalStaff assc=association7 mult=1..1 */
   public MedicalStaff medicalStaff;
   /** @pdRoleInfo migr=no name=ClinicRoom assc=association8 coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public java.util.Collection<ClinicRoom> clinicRoom;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public MedicalStaff getMedicalStaff() {
      return medicalStaff;
   }

   public void setMedicalStaff(MedicalStaff medicalStaff) {
      this.medicalStaff = medicalStaff;
   }

   /** @pdGenerated default getter */
   public java.util.Collection<ClinicRoom> getClinicRoom() {
      if (clinicRoom == null)
         clinicRoom = new java.util.HashSet<ClinicRoom>();
      return clinicRoom;
   }

   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorClinicRoom() {
      if (clinicRoom == null)
         clinicRoom = new java.util.HashSet<ClinicRoom>();
      return clinicRoom.iterator();
   }

   /** @pdGenerated default setter
     * @param newClinicRoom */
   public void setClinicRoom(java.util.Collection<ClinicRoom> newClinicRoom) {
      removeAllClinicRoom();
      for (java.util.Iterator iter = newClinicRoom.iterator(); iter.hasNext();)
         addClinicRoom((ClinicRoom)iter.next());
   }

   /** @pdGenerated default add
     * @param newClinicRoom */
   public void addClinicRoom(ClinicRoom newClinicRoom) {
      if (newClinicRoom == null)
         return;
      if (this.clinicRoom == null)
         this.clinicRoom = new java.util.HashSet<ClinicRoom>();
      if (!this.clinicRoom.contains(newClinicRoom))
         this.clinicRoom.add(newClinicRoom);
   }

   /** @pdGenerated default remove
     * @param oldClinicRoom */
   public void removeClinicRoom(ClinicRoom oldClinicRoom) {
      if (oldClinicRoom == null)
         return;
      if (this.clinicRoom != null)
         if (this.clinicRoom.contains(oldClinicRoom))
            this.clinicRoom.remove(oldClinicRoom);
   }

   /** @pdGenerated default removeAll */
   public void removeAllClinicRoom() {
      if (clinicRoom != null)
         clinicRoom.clear();
   }

}
