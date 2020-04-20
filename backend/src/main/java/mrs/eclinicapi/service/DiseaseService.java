package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.DiseaseRepository;

@Service
public class DiseaseService {

	private DiseaseRepository repository;
	
	@Autowired
	public DiseaseService(DiseaseRepository repository) {
		this.repository = repository;
	}

}
