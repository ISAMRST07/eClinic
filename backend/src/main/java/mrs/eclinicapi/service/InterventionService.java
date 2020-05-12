package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.repository.InterventionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {

    @Autowired
    private InterventionRepository repository;

    public Intervention add(Intervention newIntervention) {
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
  
}
