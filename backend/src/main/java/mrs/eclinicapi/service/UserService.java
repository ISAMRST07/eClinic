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

    public User findOne(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception NullPointerException) {

        }
    }

    public void updateUsername(Long id, String newUsername) {
        repository.updateUsername(id, newUsername);
    }

    public void updatePassword(Long id, String newPassword) {
        repository.updatePassword(id, newPassword);
    }

    public void updateName(Long id, String newName) {
        repository.updateName(id, newName);
    }

}
