package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Visit;
import mrs.eclinicapi.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private VisitRepository repository;

    @Autowired
    public VisitService(VisitRepository repository) {
        this.repository = repository;
    }

    public Visit save(Visit visit) {
        return this.repository.save(visit);
    }
}
