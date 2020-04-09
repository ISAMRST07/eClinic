package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;

import java.util.Collection;

public interface  ClinicAdministratorRepository {
    Collection<ClinicAdministrator> getAll();

    ClinicAdministrator create(ClinicAdministrator clinicAdministratorlinic);
}
