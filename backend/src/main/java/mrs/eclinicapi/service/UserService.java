package mrs.eclinicapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User addUser(User newUser){
        return repository.save(newUser);
    }
	
	public User findOne(Long id) {
		return repository.findById(id).orElseGet(null);
	}
	public List<User> findAll() {
		return repository.findAll();
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
