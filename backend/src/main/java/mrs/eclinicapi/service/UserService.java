package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;
	
	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

}
