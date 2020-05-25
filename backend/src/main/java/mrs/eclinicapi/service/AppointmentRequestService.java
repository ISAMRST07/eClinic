package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.AppointmentRequestDTO;
import mrs.eclinicapi.model.AppointmentRequest;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.repository.AppointmentRequestRepository;
import mrs.eclinicapi.repository.CodebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Beans;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentRequestService {

    private AppointmentRequestRepository repository;

    @Autowired
    public AppointmentRequestService(AppointmentRequestRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentRequest> findAll() {return repository.findAll();}

    @Transactional(isolation = Isolation.SERIALIZABLE,
            rollbackFor = AppointmentRequestDTO.ConcurrentRequest.class)
    public AppointmentRequest save(AppointmentRequest ar) throws AppointmentRequestDTO.ConcurrentRequest  {
        LocalDateTime startTime = ar.getDateTime();
        LocalDateTime endTime = startTime.plusMinutes(30);

        List<AppointmentRequest> requests = repository.findAppointmentRequestsByDoctor_Id(ar.getDoctor().getId());

        for(AppointmentRequest request : requests) {
            LocalDateTime currentStartTime = request.getDateTime();
            LocalDateTime currentEndTime = currentStartTime.plusMinutes(30);

            if (startTime.isAfter(currentStartTime) && startTime.isBefore(currentEndTime))
                return null;
            if (endTime.isAfter(currentStartTime) && endTime.isBefore(currentEndTime))
                return null;
        }

        return repository.save(ar);
    }

    public Page<AppointmentRequest> findPaged(int pageNumber, int pageSize) {
        return this.findPaged(pageNumber, pageSize, null, false);
    }

    public Page<AppointmentRequest> findPaged(int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        if(sort != null) {
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sort);
            else s = Sort.by(Sort.Direction.ASC, sort);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return repository.findAll(p);
    }

    public List<AppointmentRequest> findByClinicID(String clinicID) {
        return repository.findAppointmentRequestsByClinic_Id(clinicID);
    }

    public Page<AppointmentRequest> findByClinicIDPaged(String clinicID, int pageNumber, int pageSize) {
        return this.findByClinicIDPaged(clinicID, pageNumber, pageSize, null, false);
    }

    public Page<AppointmentRequest> findByClinicIDPaged(String clinicID, int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        if(sort != null) {
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sort);
            else s = Sort.by(Sort.Direction.ASC, sort);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return repository.findAppointmentRequestsByClinic_Id(clinicID, p);
    }

    public List<AppointmentRequest> findByPatientID(String patientID) {
        return repository.findAppointmentRequestsByPatient_Id(patientID);
    }

    public Page<AppointmentRequest> findByPatientIDPaged(String patientID, int pageNumber, int pageSize) {
        return this.findByPatientIDPaged(patientID, pageNumber, pageSize, null, false);
    }

    public Page<AppointmentRequest> findByPatientIDPaged(String patientID, int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        if(sort != null) {
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sort);
            else s = Sort.by(Sort.Direction.ASC, sort);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return repository.findAppointmentRequestsByPatient_Id(patientID, p);
    }
}
