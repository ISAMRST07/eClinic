package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class InterventionService {

    @Autowired
    private InterventionRepository repository;

    @Autowired
    private ClinicRoomRepository clinicRoomRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private InterventionTypeRepository interventionTypeRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public Intervention add(Intervention newIntervention) {
        return repository.save(newIntervention);
    }

    public Intervention findOne(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Intervention> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Intervention> getClinicIntervention(String clinicId) {
        return repository.getClinicIntervention(clinicId);
    }

}
