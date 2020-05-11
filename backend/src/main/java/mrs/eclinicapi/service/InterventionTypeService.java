package mrs.eclinicapi.service;

import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.repository.InterventionTypeRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InterventionTypeService {

    @Autowired
    private InterventionTypeRepository repository;

    public InterventionType add(InterventionType newDoctor) {
        return repository.save(newDoctor);
    }

    public InterventionType findOne(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<InterventionType> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    public List<InterventionType> getClinicInterventionType(String clinicId) {
        return repository.getClinicInterventionType(clinicId);
    }

}
