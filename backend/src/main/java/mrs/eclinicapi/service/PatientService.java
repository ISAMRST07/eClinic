package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Patient;
import mrs.eclinicapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Patient> getPatients() {
        return this.repository.findAll();
    }

    public Patient getPatientById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Transactional
    public Patient deletePatient(String id) throws Exception {
        Patient p = this.repository.findById(id).orElseThrow(Exception::new);
        this.repository.deleteById(id);
        return p;
    }

    public Patient getByUserId(String userID) {
        return repository.findPatientByUser_Id(userID);
    }
}
