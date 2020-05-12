package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.InterventionDTO;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.model.InterventionType;
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
    
    public Intervention addNewIntervention(InterventionDTO iDTO) {
    	ClinicRoom clinicRoom = clinicRoomRepository.findById(iDTO.getSelectedClinicRoom()).orElse(null);
    	Doctor doctor = doctorRepository.findById(iDTO.getSelectedDoctor()).orElse(null);
    	InterventionType interventionType = interventionTypeRepository.findById(iDTO.getSelectedInterventionType()).orElse(null);
    	
    	Intervention newIntervention = new Intervention();
    	newIntervention.setClinicRoom(clinicRoom);
    	newIntervention.setDoctor(doctor);
    	newIntervention.setInterventionType(interventionType);
    	newIntervention.setDateTime(iDTO.getDateTime());
    	newIntervention.setDuration(iDTO.getDuration());
    	newIntervention.setPrice(iDTO.getPrice());
    	
    	return repository.save(newIntervention);
    }
    
    public Intervention modify(InterventionDTO iDTO) {
    	ClinicRoom clinicRoom = clinicRoomRepository.findById(iDTO.getSelectedClinicRoom()).orElse(null);
    	Doctor doctor = doctorRepository.findById(iDTO.getSelectedDoctor()).orElse(null);
    	InterventionType interventionType = interventionTypeRepository.findById(iDTO.getSelectedInterventionType()).orElse(null);
    	
    	Intervention modified = this.findOne(iDTO.getId());
    	modified.setClinicRoom(clinicRoom);
    	modified.setDoctor(doctor);
    	modified.setInterventionType(interventionType);
    	modified.setDateTime(iDTO.getDateTime());
    	modified.setDuration(iDTO.getDuration());
    	modified.setPrice(iDTO.getPrice());
    	
    	return repository.save(modified);
    }
    
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
  
}
