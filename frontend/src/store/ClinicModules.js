import AddClinicModule from "./Clinics/AddClinicModule";
import ListClinicsModule from "./Clinics/ListClinicsModule";
import AdminClinic from "./Clinics/AdminClinic";

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule,
        readClinics: ListClinicsModule,
        adminClinic: AdminClinic
    }
};

export default ClinicModules;
