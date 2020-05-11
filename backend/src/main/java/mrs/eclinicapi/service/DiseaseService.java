package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Disease;
import mrs.eclinicapi.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    private DiseaseRepository repository;

    @Autowired
    public DiseaseService(DiseaseRepository repository) {
        this.repository = repository;
    }

    public List<Disease> findAll() {
        return repository.findAll();
    }

    public Disease add(Disease disease) {
        return repository.save(disease);
    }

    public Disease modify(Disease disease) {
        return repository.save(disease);
    }

    public Disease findOne(String id) {
        return repository.findById(id).orElseGet(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
