package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.repository.ClinicRepository;
import mrs.eclinicapi.repository.InMemoryClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Collection<Clinic> getAll() {
        return clinicRepository.getAll();
    }

    @Override
    public Clinic create(Clinic clinic) {
        // check for the
        Clinic added = clinicRepository.create(clinic);

        return added;
    }


}
