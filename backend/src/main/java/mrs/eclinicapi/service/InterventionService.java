package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {

    private InterventionRepository repository;

    @Autowired
    public InterventionService(InterventionRepository repository) {
        this.repository = repository;
    }

}
