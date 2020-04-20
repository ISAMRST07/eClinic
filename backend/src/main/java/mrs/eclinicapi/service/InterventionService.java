package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.InterventionRepository;

@Service
public class InterventionService {

	private InterventionRepository repository;
	
	@Autowired
	public InterventionService(InterventionRepository repository) {
		this.repository = repository;
	}

}
