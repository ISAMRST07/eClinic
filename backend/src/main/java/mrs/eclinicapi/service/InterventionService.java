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

    public Intervention addNewIntervention(InterventionDTO iDTO) {
    	ClinicRoom clinicRoom = clinicRoomRepository.findById(iDTO.getSelectedClinicRoom()).orElse(null);
    	Doctor doctor = doctorRepository.findById(iDTO.getSelectedDoctor()).orElse(null);
    	InterventionType interventionType = interventionTypeRepository.findById(iDTO.getSelectedInterventionType()).orElse(null);
    	Clinic clinic = clinicRepository.findById(iDTO.getClinic()).orElse(null);

    	Intervention newIntervention = new Intervention();
    	newIntervention.setClinicRoom(clinicRoom);
    	newIntervention.setDoctor(doctor);
    	newIntervention.setInterventionType(interventionType);
    	newIntervention.setClinic(clinic);
    	// TODO PREPRAVKA TREBA
    	newIntervention.setDateTime(new TimePeriod<>(iDTO.getDateTime(), iDTO.getDateTime()));
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
//    	TODO PREPRAVKA TREBA
//    	modified.setDateTime(new TimePeriod<>(iDTO.getDateTime(), iDTO.getDateTime()));
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

    public List<Intervention> getClinicIntervention(String clinicId){
    	return repository.getClinicIntervention(clinicId);
    }

}
