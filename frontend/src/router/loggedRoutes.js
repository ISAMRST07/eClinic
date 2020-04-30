import Main from "../views/Main";
import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
import ClinicAdministrators from "../pages/ClinicAdministrators";
import ClinicRooms from "../pages/ClinicRooms";

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
    }

];

export default loggedRoutes;
