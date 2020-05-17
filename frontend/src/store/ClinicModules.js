import AddClinicModule from "./Clinics/AddClinicModule";
import ListClinicsModule from "./Clinics/ListClinicsModule";
import AdminClinic from "./Clinics/AdminClinic";
import VacationRequest from "./Clinics/VacationRequest";

const ClinicModules = {
    namespaced: true,
    modules: {
        addClinic: AddClinicModule,
        readClinics: ListClinicsModule,
        adminClinic: AdminClinic,
        vacationRequest: VacationRequest
    }
};

export default ClinicModules;
