package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    private PrescriptionRepository repository;

    @Autowired
    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

}
