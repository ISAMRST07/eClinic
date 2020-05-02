package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.UnregisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnregisteredUserService {

    private UnregisteredUserRepository repository;

    @Autowired
    public UnregisteredUserService(UnregisteredUserRepository repository) {
        this.repository = repository;
    }

}
