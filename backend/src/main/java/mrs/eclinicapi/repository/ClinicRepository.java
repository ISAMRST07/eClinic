package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;

import java.util.Collection;

public interface ClinicRepository {
    Collection<Clinic> getAll();

    Clinic create(Clinic clinic);
}
