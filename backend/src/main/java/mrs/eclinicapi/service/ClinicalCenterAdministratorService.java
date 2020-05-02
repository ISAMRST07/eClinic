package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.ClinicalCentarAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalCenterAdministratorService {

    private ClinicalCentarAdministratorRepository repository;

    @Autowired
    public ClinicalCenterAdministratorService(ClinicalCentarAdministratorRepository repository) {
        this.repository = repository;
    }

}
