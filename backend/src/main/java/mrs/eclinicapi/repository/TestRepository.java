package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Test;

import java.util.Collection;

public interface TestRepository {
    Collection<Test> findTests();
}
