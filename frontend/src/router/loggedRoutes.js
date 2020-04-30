import Clinics from "../pages/Clinics";
import Home from "../pages/Home";
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
        path: '/clinicRooms',
        component: ClinicRooms,
    }

];

export default loggedRoutes;
