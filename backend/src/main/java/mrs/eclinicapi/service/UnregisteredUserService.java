package mrs.eclinicapi.service;

import mrs.eclinicapi.model.UnregisteredUser;
import mrs.eclinicapi.repository.UnregisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UnregisteredUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UnregisteredUserRepository repository;

    @Autowired
    public UnregisteredUserService(UnregisteredUserRepository repository) {
        this.repository = repository;
    }

    public UnregisteredUser addUnregisteredUser(UnregisteredUser uu) {
        uu.getUser().setPassword(passwordEncoder.encode(uu.getUser().getPassword()));
        return repository.save(uu);
    }
}
