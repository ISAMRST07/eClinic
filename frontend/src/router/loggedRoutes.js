import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
import ClinicAdministrators from "../pages/ClinicAdministrators";
import ClinicRooms from "../pages/ClinicRooms";
import Doctor from "../pages/Doctor";
import InterventionType from "../pages/InterventionType";
import Intervention from "../pages/Intervention";
import Nurse from "../pages/Nurse";
import Medicine from "../pages/Medicine";
import Disease from "../pages/Disease";
import UnregisteredUsers from "../pages/UnregisteredUsers";
import UserProfileView from "../components/UserProfile/UserProfileView";
import ClinicDetails from "../pages/ClinicDetails";
import Patients from "../pages/Patients";
import DoctorHome from "../pages/DoctorHome";

import {
    clinicAdministratorsPath,
    clinicDetailsPath,
    clinicRoomsPath,
    clinicsPath,
    diseasePath,
    doctorsPath,
    nursesPath,
    homePath,
    medicinesPath,
    unregisteredUsersPath,
    interventionTypePath,
    interventionPath,
    userProfilePath,
    patientsPath,
    doctorHomePath
} from "../utils/paths";

const loggedRoutes = [
    {
        path: homePath,
        component: Home,
    },
    {
        path: clinicsPath,
        component: Clinics,
    },
    {
        path: clinicAdministratorsPath,
        component: ClinicAdministrators,
    },

    {
        path: clinicRoomsPath,
        component: ClinicRooms,
    },

    {
        path: doctorsPath,
        component: Doctor,
    },

    {
        path: nursesPath,
        component: Nurse,
    },

    {
        path: medicinesPath,
        component: Medicine,
    },
    {
        path: diseasePath,
        component: Disease,
    },
    {
        path: unregisteredUsersPath,
        component: UnregisteredUsers
    },
    {
    	path: clinicDetailsPath,
    	component: ClinicDetails
    },
    {
    	path : interventionTypePath,
    	component : InterventionType
    },
    {
    	path : interventionPath,
    	component : Intervention
    },
    {
        path: userProfilePath,
        component: UserProfileView
    }
    ,
    {
        path: patientsPath,
        component: Patients
    },
    {
        path: doctorHomePath,
        component: DoctorHome
    },

];

export default loggedRoutes;
