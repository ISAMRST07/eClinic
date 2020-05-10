package mrs.eclinicapi.service;


import mrs.eclinicapi.model.*;
import mrs.eclinicapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;

    public Clinic addClinic(Clinic newClinic) {
        return clinicRepository.save(newClinic);
    }

    public Clinic findOne(String id) {
        return clinicRepository.findById(id).orElse(null);
    }

    @Transactional
    public Clinic modifyClinic(Clinic clinic) {
        Clinic toModify = this.findOne(clinic.getId());
        if (toModify == null) return null;
        toModify.setName(clinic.getName());
        toModify.setCoordinates(clinic.getCoordinates());
        toModify.setDescription(clinic.getDescription());
        toModify.setAddress(clinic.getAddress());
        return clinicRepository.save(toModify);
    }

    public Clinic findByUser(String userID){
        Optional<User> user = userRepository.findById(userID);
        if(!user.isPresent()){
            System.out.println("NE IDEEEE");
            return null;
        }
        System.out.println("DAKHE IDEEEE");
        Optional<ClinicAdministrator> clinicAdministrator =  clinicAdministratorRepository.findClinicAdministratorByUser(user.get());
        Optional<Doctor> doctor =  doctorRepository.findDoctorByUser(user.get());
        Optional<Nurse> nurse =  nurseRepository.findNurseByUser(user.get());
        if(clinicAdministrator.isPresent()) return  clinicAdministrator.get().getClinic();
        if(doctor.isPresent()) return  doctor.get().getClinic();
        if(nurse.isPresent()) return  nurse.get().getClinic();
        return null;
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public void deleteById(String id) {
        clinicRepository.deleteById(id);
    }

    public void deleteAll() {
        clinicRepository.deleteAll();
    }

    public void deleteOne(Clinic clinicToDelete) {
        clinicRepository.delete(clinicToDelete);
    }
}
