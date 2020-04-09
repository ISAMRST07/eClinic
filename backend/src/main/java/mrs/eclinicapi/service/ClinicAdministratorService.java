package mrs.eclinicapi.service;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.Test;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import mrs.eclinicapi.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdministratorService {
    @Autowired
    private ClinicAdministratorRepository clinicAdminRepository;

    public boolean addClinicAdministraor(ClinicAdministrator newCAdministrator){
        ClinicAdministrator added = clinicAdminRepository.create(newCAdministrator);

        return true;
    }
}
