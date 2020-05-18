import {
    clinicAdministratorsPath,
    clinicRoomsPath,
    clinicsPath,
    homePath,
    medicinesPath,
    unregisteredUsersPath,
    doctorsPath,
    nursesPath,
    clinicDetailsPath,
    interventionTypePath,
    interventionPath,
    patientsPath,
    doctorHomePath,
    vacationRequestPath
} from "./paths";

const doctorsItem = {
    icon: 'mdi-account-tie',
    label: 'Doctors',
    path: doctorsPath
};

const nursesItem = {
    icon: 'mdi-account-tie',
    label: 'Nurses',
    path: nursesPath
};

const homeItem = {
    icon: 'mdi-home',
    label: 'Home',
    path: homePath
};

function clinicDetailsItem(id) {
    return {
        icon: 'mdi-home',
        label: 'Clinic',
        path: clinicDetailsPath.replace(":id", id)
    };
}

const interventionTypeItem = {
    icon: 'mdi-currency-eur',
    label: 'Intervention types and pricing',
    path: interventionTypePath
};

const interventionItem = {
    icon: 'mdi-home',
    label: 'Interventions',
    path: interventionPath
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

const patientsItem = {
    icon: 'mdi-account-heart',
    label: 'Patients',
    path: patientsPath,
};

const doctorHomeItem = {
    icon: 'mdi-home',
    label: 'Doctor Home',
    path: doctorHomePath,
};

const vacationRequestItem = {
	icon: 'mdi-home',
	label: 'Vacation request',
    path: vacationRequestPath,
}

const ClinicalCenterAdmin = {
    name: 'Clinical Center Admin',
    code: 'clinicCentarAdministrator',
    items: [
        homeItem,
        clinicsItem,
        medicinesItem,
        clinicAdminsItem,
        clinicRoomsItem,
        patientsItem,
        unregisteredUsersItem,
    ],
};


class ClinicalAdmin {
    static name = 'Clinical Admin';
    static code = 'clinicAdministrator';
    constructor(id) {
        this.items = [
            clinicDetailsItem(id),
            interventionTypeItem,
            interventionItem,
            medicinesItem,
            clinicRoomsItem,
            doctorsItem,
            nursesItem,
            vacationRequestItem
        ]
    }
}


const Patient = {
    name: 'Patient',
    code: 'Patient',
    items: [
        homeItem,
        clinicsItem,
        interventionTypeItem,

    ]
};

const Doctor = {
	name: 'Doctor',
    code: 'doctor',
    items: [
        doctorHomeItem,
        vacationRequestItem
    ]	
}
export {ClinicalCenterAdmin, ClinicalAdmin, Patient, Doctor};
