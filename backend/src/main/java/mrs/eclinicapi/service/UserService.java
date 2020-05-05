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
        return repository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception NullPointerException) {

        }
    }

    public void updateEmail(String id, String newEmail) {
        repository.updateEmail(id, newEmail);
    }

    public void updatePassword(String id, String newPassword) {
        repository.updatePassword(id, newPassword);
    }

    public void updateName(String id, String newName) {
        repository.updateName(id, newName);
    }

}
