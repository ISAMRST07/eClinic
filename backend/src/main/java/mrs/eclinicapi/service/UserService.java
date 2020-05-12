package mrs.eclinicapi.service;

import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User addUser(User newUser) {
        return repository.save(newUser);
    }

    public User findOne(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception NullPointerException) {
            // pa metni mu nesto odje
        }
    }

    @Transactional
    public User updateUser(String id, User u) {
        User toModify = repository.findById(id).orElse(null);
        if(toModify == null) return null;
        toModify.setAddress(u.getAddress());
        toModify.setCity(u.getCity());
        toModify.setCountry(u.getCountry());
        toModify.setName(u.getName());
        toModify.setSurname(u.getSurname());
        toModify.setPhoneNumber(u.getPhoneNumber());
        return repository.save(toModify);
    }
}
