package mrs.eclinicapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.repository.DoctorRepository;

@Transactional
@Service
public class DoctorService {

	@Autowired
	private DoctorRepository repository;
	
	public Doctor addDoctor(Doctor newDoctor){
        return repository.save(newDoctor);
    }
	
	public Doctor findOne(Long id) {
		return repository.findById(id).orElseGet(null);
	}
	
	public List<Doctor> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public void updatePosition(Long id, String newPosition) {
		repository.updatePosition(id, newPosition);
	}
}
