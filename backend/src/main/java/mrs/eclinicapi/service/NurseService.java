package mrs.eclinicapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.eclinicapi.model.Nurse;
import mrs.eclinicapi.repository.NurseRepository;

@Transactional
@Service
public class NurseService {

	@Autowired
	private NurseRepository repository;
	
	public Nurse addNurse(Nurse newNurse){
        return repository.save(newNurse);
    }
	
	public Nurse findOne(Long id) {
		return repository.findById(id).orElseGet(null);
	}
	
	public List<Nurse> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
