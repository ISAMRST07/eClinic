package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Test;

import java.util.Collection;

public interface TestService {
    Collection<Test> findTests();
}
