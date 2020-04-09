package mrs.eclinicapi.model; /***********************************************************************
 * Module:  MedicalStaff.java
 * Author:  Manojlović
 * Purpose: Defines the Class MedicalStaff
 ***********************************************************************/

import java.util.*;

/** @pdOid a25e35db-f133-450b-af54-44fb0f84fc94 */
public class MedicalStaff extends User {
   /** @pdRoleInfo migr=no name=Pacijent assc=association4 mult=0..* */
   public Pacijent[] pacijent;
   /** @pdRoleInfo migr=no name=WorkingCalendar assc=association5 mult=1..1 */
   public WorkingCalendar workingCalendar;
   /** @pdRoleInfo migr=no name=Clinic assc=association7 mult=0..* side=A */
   public Clinic[] clinic;

}
