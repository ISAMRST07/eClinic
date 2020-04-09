package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryClinicRepository implements ClinicRepository{
    private final ConcurrentMap<String, Clinic> clinics = new ConcurrentHashMap<>();

    @Override
    public Collection<Clinic> getAll() {
        return this.clinics.values();
    }

    @Override
    public Clinic create(Clinic clinic) {
        if(clinics.containsKey(clinic.getId())){
            return null;
        }
        this.clinics.put(clinic.getId(), clinic);
        return clinic;
    }

}
