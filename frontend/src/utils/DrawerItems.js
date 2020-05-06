import {
    clinicAdministratorsPath,
    clinicRoomsPath,
    clinicsPath,
    homePath,
    medicinesPath,
    unregisteredUsersPath
} from "./paths";
const homeItem = {
    icon: 'mdi-home',
    label: 'Home',
    path: homePath
};

const clinicsItem = {
    icon: 'mdi-hospital-building',
    label: 'Clinics',
    path: clinicsPath
};

const medicinesItem = {
    icon: 'mdi-pill',
    label: 'Medicine',
    path: medicinesPath
};

const clinicAdminsItem = {
    icon: 'mdi-account-tie',
    label: 'Clinic Administrators',
    path: clinicAdministratorsPath
};

const clinicRoomsItem = {
    icon: 'mdi-bed-empty',
    label: 'Clinic Rooms',
    path: clinicRoomsPath
};

const unregisteredUsersItem = {
    icon: 'mdi-account-multiple-check',
    label: 'User requests',
    path: unregisteredUsersPath,
};

const ClinicalCenterAdmin = {
    name: 'Clinical Center Admin',
    code: 'clinicCentarAdministrator',
    items: [
        homeItem,
        clinicsItem,
        medicinesItem,
        clinicAdminsItem,
        clinicRoomsItem,
        unregisteredUsersItem
    ],
};

export { ClinicalCenterAdmin };
