package mrs.eclinicapi.service;


import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic addClinic(Clinic newClinic) {
        return clinicRepository.save(newClinic);
    }

    public Clinic findOne(Long id) {
        return clinicRepository.findById(id).orElseGet(null);
    }

    public Clinic modifyClinic(Clinic clinic) {
        if (this.findOne(clinic.getId()) == null) return null;
        return clinicRepository.save(clinic);
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public void deleteById(Long id) {
        clinicRepository.deleteById(id);
    }

    public void deleteAll() {
        clinicRepository.deleteAll();
    }

    public void deleteOne(Clinic clinicToDelete) {
        clinicRepository.delete(clinicToDelete);
    }
}
