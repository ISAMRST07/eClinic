package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Pacijent.java
 * Author:  Manojlović
 * Purpose: Defines the Class Pacijent
 ***********************************************************************/

import java.util.*;

/** @pdOid 003a239a-4d75-488d-88de-bd037b70a646 */
public class Pacijent extends User {
   /** @pdRoleInfo migr=no name=MedicalRecord assc=association1 mult=1..1 */
   public MedicalRecord medicalRecord;
   /** @pdRoleInfo migr=no name=Intervention assc=association17 mult=1..* */
   public Intervention[] intervention;

}
