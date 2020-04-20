package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.DoctorRepository;

@Service
public class DoctorService {

	private DoctorRepository repository;
	
	@Autowired
	public DoctorService(DoctorRepository repository) {
		this.repository = repository;
	}

}
