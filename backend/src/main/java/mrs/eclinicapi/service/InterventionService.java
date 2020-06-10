package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public Intervention add(Intervention newIntervention) {
        Doctor d = newIntervention.getDoctor();
        d.setChangeTime(LocalDateTime.now());
        doctorRepository.save(d);
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

    public List<Intervention> getPatientIntervention(String patientId) {
        return repository.findInterventionsByPatient_IdAndVisitNotNull(patientId);
    }

    public Intervention findUpcoming(String doctorID, String patientID) {
        List<Intervention> interventions = repository.findInterventionsByDoctor_IdAndPatient_Id(doctorID, patientID);
        if(interventions.isEmpty()) return null;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteen = now.plusMinutes(20);
        return interventions.stream().filter(in -> (in.getDateTime().getStart().isAfter(now)
                && in.getDateTime().getStart().isBefore(fifteen))
                || (in.getDateTime().getStart().isBefore(now) && in.getDateTime().getEnd().isAfter(now))
                && in.getVisit() == null).findFirst().orElse(null);
    }

    public Intervention findOngoing(String doctorID) {
        List<Intervention> interventions = repository.findInterventionsByDoctor_Id(doctorID);
        if(interventions.isEmpty()) return null;
        LocalDateTime now = LocalDateTime.now();
        return interventions.stream().filter(in -> in.getDateTime().getStart().isBefore(now)
                && in.getDateTime().getEnd().isAfter(now)
                && in.getVisit() == null).findFirst().orElse(null);
    }

    public boolean pastIntervention(String doctorID, String patientID) {
        List<Intervention> interventions = repository.findInterventionsByDoctor_IdAndPatient_Id(doctorID, patientID);
        return interventions.stream().anyMatch(in -> in.getVisit() != null);
    }

}
