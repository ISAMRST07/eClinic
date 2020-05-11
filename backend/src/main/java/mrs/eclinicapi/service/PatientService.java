package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Patient;
import mrs.eclinicapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient addPatient(Patient p) {
        return this.repository.save(p);
    }
}
