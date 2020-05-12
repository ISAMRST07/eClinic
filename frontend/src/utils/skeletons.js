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


/*
*
*           TODO
*           EMPTY DOCTOR I EMPTY NURSE SE NE POKLAPAJU SA MODELOM!
*
*
* */

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
    duration: 0,
    price: 0
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

/*
*
*           TODO
*           EMPTY DOCTOR I EMPTY NURSE SE NE POKLAPAJU SA MODELOM
*
*
* */

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
