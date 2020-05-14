package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdminRepository, PasswordEncoder passwordEncoder) {
        this.clinicAdminRepository = clinicAdminRepository;
        this.passwordEncoder = passwordEncoder;
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
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);
        clinicAdministrator.setClinic(clinicAdministratorDto.getClinic());


        return new ClinicAdministratorDto(clinicAdminRepository.save(clinicAdministrator), 0);
    }

    @Transactional
    public ClinicAdministratorDto updateClinicAdministraor(ClinicAdministratorDto clinicAdministratorDto) {
        User newUser = new User(clinicAdministratorDto);
        newUser.setId(clinicAdministratorDto.getUserID());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        ClinicAdministrator clinicAdministrator = new ClinicAdministrator();
        clinicAdministrator.setUser(newUser);
        clinicAdministrator.setId(clinicAdministratorDto.getId());
        clinicAdministrator.setClinic(clinicAdministratorDto.getClinic());
        return new ClinicAdministratorDto(clinicAdminRepository.save(clinicAdministrator), 0);
    }

    public List<ClinicAdministratorDto> findAll() {
        List<ClinicAdministrator> clinicAdministrators = clinicAdminRepository.findAll();

        List<ClinicAdministratorDto> clinicAdministratorsDto = new ArrayList<>();
        for (ClinicAdministrator c : clinicAdministrators) {
            ClinicAdministratorDto cdto = new ClinicAdministratorDto(c, 0);
            clinicAdministratorsDto.add(cdto);
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
