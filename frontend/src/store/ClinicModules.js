import AddClinicModule from "./Clinics/AddClinicModule";
import ListClinicsModule from "./Clinics/ListClinicsModule";

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule,
        readClinics: ListClinicsModule
    }
};

export default ClinicModules;
