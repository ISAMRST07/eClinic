import Main from "../views/Main";
import Clinics from "../pages/Clinics";
import Home from "../pages/Home";

const loggedRoutes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/clinics',
        component: Clinics,
    },

];

export default loggedRoutes;
