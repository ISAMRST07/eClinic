package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Patient;
import mrs.eclinicapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
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

    public List<Patient> getByClinicId(String clinicID) {
        List<Patient> patients = this.repository.findAll();
        ArrayList<Patient> patientsToSend = new ArrayList<>();
        for (Patient p: patients) {
            for (Clinic c: p.getClinic()) {
                if(c.getId().equalsIgnoreCase(clinicID)) {
                    patientsToSend.add(p);
                    break;
                }
            }
        }
        return patientsToSend;

    }

    public Patient deletePatient(String id) throws Exception {
        Patient p = this.repository.findById(id).orElseThrow(Exception::new);
        this.repository.deleteById(id);
        return p;
    }

    public Patient getByUserId(String userID) {
        return repository.findPatientByUser_Id(userID);
    }
}
