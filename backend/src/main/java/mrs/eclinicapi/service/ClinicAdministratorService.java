package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.Test;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import mrs.eclinicapi.repository.ClinicRepository;
import mrs.eclinicapi.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdminRepository;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdminRepository, UserRepositroy userRepositroy) {
        this.clinicAdminRepository = clinicAdminRepository;
    }

    @Transactional
    public ClinicAdministrator addClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto){
        User newUser = new User(clinicAdministratorDto.getUsername(), clinicAdministratorDto.getPassword(),
                clinicAdministratorDto.getName(), clinicAdministratorDto.getType(), null);
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);

        return clinicAdminRepository.save(clinicAdministrator);
    }
}
