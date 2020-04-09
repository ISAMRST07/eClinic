package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Prescription.java
 * Author:  Manojlović
 * Purpose: Defines the Class Prescription
 ***********************************************************************/

import java.util.*;

/** @pdOid 4ae36bab-979f-4e15-acd2-65c5c65b566e */
public class Prescription {
   /** @pdOid c9bc8b13-9003-47cf-b5d2-52dac5aaef15 */
   private int id;

   /** @pdRoleInfo migr=no name=Medicine assc=association20 mult=1..1 */
   public Medicine medicine;

}
