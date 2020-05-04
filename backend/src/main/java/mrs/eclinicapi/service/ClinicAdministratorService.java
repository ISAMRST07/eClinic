package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import mrs.eclinicapi.repository.ClinicRepository;
import mrs.eclinicapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdminRepository;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdminRepository, ClinicRepository clinicRepository, UserRepository userRepository) {
        this.clinicAdminRepository = clinicAdminRepository;
    }

    @Transactional
    public ClinicAdministratorDto addClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto) {
//        if(this.clinicAdminRepository.findByUsername(clinicAdministratorDto.getUsername())==null){
//            System.out.println("Sokole postoji ovaj");
//        }
//        else{
//            System.out.println("Sokole ne postoji ovaj");
//        }
        User newUser = new User(clinicAdministratorDto);
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);
        clinicAdministrator.setClinic(clinicAdministratorDto.getClinic());

        return new ClinicAdministratorDto(clinicAdminRepository.save(clinicAdministrator), 0);
    }

    @Transactional
    public ClinicAdministratorDto updateClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto) {
        User newUser = new User(clinicAdministratorDto);
        newUser.setId(clinicAdministratorDto.getUserID());
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);
        clinicAdministrator.setId(clinicAdministratorDto.getId());
        clinicAdministrator.setClinic(clinicAdministratorDto.getClinic());
        return new ClinicAdministratorDto(clinicAdminRepository.save(clinicAdministrator), 0);
    }

    public List<ClinicAdministratorDto> findAll() {
        List<ClinicAdministrator> clinicAdministrators =  clinicAdminRepository.findAll();

        List<ClinicAdministratorDto> clinicAdministratorsDto = new ArrayList<>();
        for (ClinicAdministrator c : clinicAdministrators) {
            clinicAdministratorsDto.add(new ClinicAdministratorDto(c, 0));
        }
        return clinicAdministratorsDto;
    }

    public ClinicAdministrator findById(String id) {
        return clinicAdminRepository.findById(id).orElseGet(null);
    }

    @Transactional
    public void deleteById(String id) {
        clinicAdminRepository.deleteById(id);
    }
}
