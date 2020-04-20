package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.NurseRepository;

@Service
public class NurseService {

	private NurseRepository repository;
	
	@Autowired
	public NurseService(NurseRepository repository) {
		this.repository = repository;
	}

}
