package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.InterventionDTO;
import mrs.eclinicapi.model.*;
import mrs.eclinicapi.repository.ClinicRepository;
import mrs.eclinicapi.repository.ClinicRoomRepository;
import mrs.eclinicapi.repository.DoctorRepository;
import mrs.eclinicapi.repository.InterventionRepository;
import mrs.eclinicapi.repository.InterventionTypeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Intervention> getClinicIntervention(String clinicId){
    	return repository.getClinicIntervention(clinicId);
    }

}
