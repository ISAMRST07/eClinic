import AddClinicModule from "./Clinics/AddClinicModule";
import ListClinicsModule from "./Clinics/ListClinicsModule";
import OneClinicModule from "./Clinics/OneClinicModule";

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule,
        readClinics: ListClinicsModule,
        oneClinic : OneClinicModule
    }
};

export default ClinicModules;
