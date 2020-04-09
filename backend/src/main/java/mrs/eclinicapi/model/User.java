package mrs.eclinicapi.model; /***********************************************************************
 * Module:  User.java
 * Author:  Manojlović
 * Purpose: Defines the Class User
 ***********************************************************************/

import java.util.*;

/** @pdOid 85433257-248d-4b5d-a4f7-715f6e82b0c1 */
public abstract class User {
   /** @pdOid 36701ae4-7c08-465d-926b-423a8c62f0b5 */
   private String username;
   /** @pdOid 00d015a4-0cd0-49d2-8141-54c049d4f923 */
   private String password;
   /** @pdOid cd5ac836-2bcd-46ce-ada6-3bda18d8d3a1 */
   private String name;
   /** @pdOid 4d94cf08-1362-4825-a924-a14c57131e23 */
   private UnregistredUser type;
   /** @pdOid 8ca7fb96-5cce-43e5-a718-426561247acc */
   private String id;

}
