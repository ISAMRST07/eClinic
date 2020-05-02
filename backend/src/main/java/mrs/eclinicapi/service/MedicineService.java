package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    private MedicineRepository repository;

    @Autowired
    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

}
