package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.ExaminationRepository;

@Service
public class ExaminationService {

	private ExaminationRepository repository;
	
	@Autowired
	public ExaminationService(ExaminationRepository repository) {
		this.repository = repository;
	}

}
