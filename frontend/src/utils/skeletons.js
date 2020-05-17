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
    phoneNumber: '',
    clinic : null
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
	id : null,
	email : '',
	password : '',
	name : '',
	surname : '',
	phoneNumber : '',
	address : '',
	city : '',
	country : '',
	personalID : '',
	specialties: [],
    clinicID: null
};

export const emptyNurse = {
    id : null,
    email : '',
    password : '',
    name : '',
    surname : '',
    phoneNumber : '',
    address : '',
    city : '',
    country : '',
    personalID : '',
    clinicID: null
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
	clinic : '',
    duration: 0,
    price: 0
};

export const emptyVacationRequest = {
	id : '',
	user: '',
    clinic: '',
	startDate: null,
    endDate: null,
    approved: null
};

export const emptyUser = {
    email: '',
    name: '',
    surname: '',
    phoneNumber: '',
    address: '',
    city: '',
    country: '',
    personalID: '',
};

export const emailPattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
