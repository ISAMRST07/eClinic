package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.UnregistredUserRepository;

@Service
public class UnregistredUserService {

	private UnregistredUserRepository repository;
	
	@Autowired
	public UnregistredUserService(UnregistredUserRepository repository) {
		this.repository = repository;
	}

}
