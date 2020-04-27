package mrs.eclinicapi.model.enums; /***********************************************************************
 * Module:  UserType.java
 * Author:  Manojlović
 * Purpose: Defines the Class UserType
 ***********************************************************************/

/**
 * @pdOid 98f01ff3-a11a-409a-bf3e-d05f81680e22
 */
public enum UserType {
    unregistredUser,
    clinicCentarAdministrator,
    clinicAdministrator,
    doctor,
    nurse,
    Patient;
	
	public static UserType fromInteger(int x) {
        switch(x) {
        case 0:
            return unregistredUser;
		case 1:
            return clinicCentarAdministrator;
        case 2:
            return clinicAdministrator;
        case 3:
            return doctor;
        case 4:
            return nurse;
        case 5:
            return Patient;            
        }
        return null;
    }
}
