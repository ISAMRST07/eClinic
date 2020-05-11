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
    country : '',
    email : '',
    personalID : '',
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

export const emptyDoctor = {
	email : '',
	name : '',
	surname : '',
	phone : '',
	address : '',
	city : '',
	country : '',
	jmbg : '',
	position: '',
    clinic : ''
};

export const emptyInterventionType = {
	id : '',
	name : '',
	price : '',
	clinic : ''
};

export const emptyIntervention = {
	id : '',
	dateTime: null,
    selectedClinicRoom: null,
    selectedDoctor: null,
    selectedInterventionType: '',
    duration: '',
    price: ''
};

export const emptyNurse = {
	id : '',
	email : '',
	name : '',
	surname : '',
	phone : '',
	address : '',
	city : '',
	country : '',
	jmbg : '',
	position: ''
};

export const emailPattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
