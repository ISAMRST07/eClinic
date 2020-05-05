export const emptyClinic = {
    name: '',
    description: '',
    address: null,
    coordinates: null
};

export const emptyClinicAdmin = {
    name: '',
    surname: '',
    password: '',
    username: '',
    city : '',
    state : '',
    email : '',
    umnc : '',
    address: '',
    clinic : null
};

export const resetClinic = {
    name: '',
    description: '',
    address: null,
    coordinates: null,
    reset: true
};

export const emptyDisease = {
    name: null
};

export const emptyMedicine = {
    name: null,
    manufacturer: null
};

export const emptyClinicRoom = {
    name: null,
    clinicId: null
};

export const emptyDoctor = null; // ovo brate doradi da ja ne pravim. to je tri linije koda

export const emailPattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
