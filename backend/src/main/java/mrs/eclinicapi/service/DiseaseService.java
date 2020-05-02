package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

    private DiseaseRepository repository;

    @Autowired
    public DiseaseService(DiseaseRepository repository) {
        this.repository = repository;
    }

}
