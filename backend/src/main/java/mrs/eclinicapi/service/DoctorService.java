package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Doctor addDoctor(Doctor newDoctor) {
        return repository.save(newDoctor);
    }

    public Doctor findOne(String id) {
        return repository.findById(id).orElseGet(null);
    }

    public List<Doctor> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Doctor> getDoctorsForClinic(String id) {
        System.out.println("getDoctorsForClinic service");
        return repository.getDoctorsForClinic(id);
    }
}
