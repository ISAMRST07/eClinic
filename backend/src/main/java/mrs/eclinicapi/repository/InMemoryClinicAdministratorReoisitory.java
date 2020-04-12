package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryClinicAdministratorReoisitory implements ClinicAdministratorRepository{
    private final ConcurrentMap<Long, ClinicAdministrator> clinicsAdmins = new ConcurrentHashMap<>();

    @Override
    public Collection<ClinicAdministrator> getAll() {
        return clinicsAdmins.values();
    }

    @Override
    public ClinicAdministrator create(ClinicAdministrator clinicAdministrator) {
        if(clinicsAdmins.containsKey(clinicAdministrator.getId())){
            return null;
        }
        this.clinicsAdmins.put(clinicAdministrator.getId(), clinicAdministrator);
        return clinicAdministrator;
    }

}
