package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Clinic;

import java.util.Collection;

public interface ClinicService {
    Collection<Clinic> getAll();
    Clinic create(Clinic clinic);
}
