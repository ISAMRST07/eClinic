package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	private PrescriptionRepository repository;
	
	@Autowired
	public PrescriptionService(PrescriptionRepository repository) {
		this.repository = repository;
	}

}
