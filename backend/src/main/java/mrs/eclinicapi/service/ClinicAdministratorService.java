package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.Test;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import mrs.eclinicapi.repository.ClinicRepository;
import mrs.eclinicapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdminRepository;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdminRepository, UserRepository userRepository) {
        this.clinicAdminRepository = clinicAdminRepository;
    }

    @Transactional
    public ClinicAdministrator addClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto){
        User newUser = new User(clinicAdministratorDto.getUsername(), 
        		clinicAdministratorDto.getPassword(),
                clinicAdministratorDto.getName(),
                clinicAdministratorDto.getSurname(),
                UserType.clinicAdministrator);
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);

        return clinicAdminRepository.save(clinicAdministrator);
    }

    @Transactional
    public ClinicAdministrator updateClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto){
        User newUser = new User(clinicAdministratorDto.getUsername(),
                clinicAdministratorDto.getPassword(),
                clinicAdministratorDto.getName(),
                clinicAdministratorDto.getSurname(),
                UserType.clinicAdministrator);
        newUser.setId(clinicAdministratorDto.getId());
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);
        clinicAdministrator.setId(clinicAdministratorDto.getId());

        return clinicAdminRepository.save(clinicAdministrator);
    }

    public List<ClinicAdministrator> findAll() {
        return clinicAdminRepository.findAll();
    }

    public ClinicAdministrator findById(Long id) {
        return clinicAdminRepository.findById(id).orElseGet(null);
    }

    @Transactional
    public void deleteById(Long id) {
        clinicAdminRepository.deleteById(id);
    }
}
