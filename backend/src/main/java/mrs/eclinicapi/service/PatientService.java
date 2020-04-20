package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository repository;
	
	@Autowired
	public PatientService(PatientRepository repository) {
		this.repository = repository;
	}

}
