import Main from "../views/Main";
import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
import ClinicAdministrators from "../pages/ClinicAdministrators";
import ClinicRooms from "../pages/ClinicRooms";
import Doctor from "../pages/Doctor";
import Nurse from "../pages/Nurse";
import Medicine from "../pages/Medicine";
import {
    clinicAdministratorsPath,
    clinicRoomsPath,
    clinicsPath, diseasePath,
    doctorsPath,
    nursesPath,
    homePath,
    medicinesPath, unregisteredUsersPath
} from "../utils/paths";
import Disease from "../pages/Disease";
import UnregisteredUsers from "../pages/UnregisteredUsers";

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
    }

];

export default loggedRoutes;
