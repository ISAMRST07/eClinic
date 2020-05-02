package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {

    private ExaminationRepository repository;

    @Autowired
    public ExaminationService(ExaminationRepository repository) {
        this.repository = repository;
    }

}
