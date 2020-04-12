package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Test;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryTestRepository implements TestRepository {
    private final ConcurrentMap<Integer, Test> tests = new ConcurrentHashMap<Integer, Test>();

    @Override
    public Collection<Test> findTests() {
        Test t = new Test();
        t.setId(1L);
        tests.putIfAbsent(1, t);
        return this.tests.values();
    }
}
