package mrs.eclinicapi.service;

import mrs.eclinicapi.model.AppointmentRequest;
import mrs.eclinicapi.repository.AppointmentRequestRepository;
import mrs.eclinicapi.repository.CodebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentRequestService {

    private AppointmentRequestRepository repository;

    @Autowired
    public AppointmentRequestService(AppointmentRequestRepository repository) {
        this.repository = repository;
    }

    public List<AppointmentRequest> findAll() {return repository.findAll();}

    public AppointmentRequest save(AppointmentRequest ar) { return repository.save(ar); }
}
