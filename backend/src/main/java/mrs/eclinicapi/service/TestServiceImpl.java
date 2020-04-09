package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Test;
import mrs.eclinicapi.repository.InMemoryTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private InMemoryTestRepository testRepository;

    @Override
    public Collection<Test> findTests() {
        return testRepository.findTests();
    }
}
