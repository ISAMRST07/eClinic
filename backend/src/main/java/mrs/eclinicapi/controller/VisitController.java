package mrs.eclinicapi.controller;

import mrs.eclinicapi.dto.DiagnosisDTO;
import mrs.eclinicapi.dto.MedicineDTO;
import mrs.eclinicapi.dto.VisitDTO;
import mrs.eclinicapi.model.Diagnosis;
import mrs.eclinicapi.model.Medicine;
import mrs.eclinicapi.model.VacationRequest;
import mrs.eclinicapi.model.Visit;
import mrs.eclinicapi.service.DiagnosisService;
import mrs.eclinicapi.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/visit", produces = MediaType.APPLICATION_JSON_VALUE)
public class VisitController {

    @Autowired
    VisitService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VisitDTO>> getAllVisits() {
        List<Visit> it = service.findAll();
        return new ResponseEntity<>(it.stream().map(this::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/clinic/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VisitDTO>> getAllVisitsByClinic(@PathVariable("clinicId") String clinicId) {
        List<Visit> it = service.findAllByClinic(clinicId);
        return new ResponseEntity<>(it.stream().map(this::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping(path ="/certified/{visitId}")
    public ResponseEntity<VisitDTO> updateMedicine(@PathVariable("visitId") String visitId) {
        System.out.println(visitId);
        Optional<Visit> visit = service.findById(visitId);

        if(visit.isPresent()){
            visit.get().setCertified(true);
            Visit modify = service.modify(visit.get());
            if(modify== null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(this.convertToDTO(modify), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private VisitDTO convertToDTO(Visit visit) {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setCertified(visit.isCertified());
        visitDTO.setAnamnesis(visit.getAnamnesis());
        visitDTO.setId(visit.getId());
        visitDTO.setDoctorName(visit.getIntervention().getDoctor().getUser().getName());
        visitDTO.setMedicines(visit.getMedicines());
        visitDTO.setDiagnoses(visit.getDiagnoses());
        visitDTO.setPatientName(visit.getIntervention().getPatient().getUser().getName());

        return visitDTO;
    }
}
