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

    public Clinic findOne(String id) {
        return clinicRepository.findById(id).orElse(null);
    }

    @Transactional
    public Clinic modifyClinic(Clinic clinic) {
        Clinic toModify = this.findOne(clinic.getId());
        if (toModify == null) return null;
        toModify.setName(clinic.getName());
        toModify.setCoordinates(clinic.getCoordinates());
        toModify.setDescription(clinic.getDescription());
        toModify.setAddress(clinic.getAddress());
        return clinicRepository.save(toModify);
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public void deleteById(String id) {
        clinicRepository.deleteById(id);
    }

    public void deleteAll() {
        clinicRepository.deleteAll();
    }

    public void deleteOne(Clinic clinicToDelete) {
        clinicRepository.delete(clinicToDelete);
    }
}
