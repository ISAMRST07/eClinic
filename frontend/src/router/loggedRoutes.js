import Main from "../views/Main";
import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
import ClinicAdministrators from "../pages/ClinicAdministrators";
import ClinicRooms from "../pages/ClinicRooms";
import Doctor from "../pages/Doctor";
import Medicine from "../pages/Medicine";

const loggedRoutes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/clinics',
        component: Clinics,
    },
    {
        path: '/clinicAdministrators',
        component: ClinicAdministrators,
    },

    {
        path: '/clinicRooms',
        component: ClinicRooms,
    },

    {
        path: '/doctor',
        component: Doctor,
    },

    {
        path: '/medicine',
        component: Medicine,
    }


];

export default loggedRoutes;
