package mrs.eclinicapi.service;


import com.sun.org.apache.xpath.internal.operations.Bool;
import mrs.eclinicapi.model.*;
import mrs.eclinicapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public Clinic findByUser(String userID) {
        Optional<User> user = userRepository.findById(userID);
        if (!user.isPresent()) {
            System.out.println("NE IDEEEE");
            return null;
        }
        System.out.println("DAKHE IDEEEE");
        Optional<ClinicAdministrator> clinicAdministrator = clinicAdministratorRepository.findClinicAdministratorByUser(user.get());
        Optional<Doctor> doctor = doctorRepository.findDoctorByUser(user.get());
        Optional<Nurse> nurse = nurseRepository.findNurseByUser(user.get());
        if (clinicAdministrator.isPresent()) return clinicAdministrator.get().getClinic();
        if (doctor.isPresent()) return doctor.get().getClinic();
        if (nurse.isPresent()) return nurse.get().getClinic();
        return null;
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public void deleteById(String id) {
        clinicRepository.deleteById(id);
    }

    public Page<Clinic> findPaged(int pageNumber, int pageSize) {
        return this.findPaged(pageNumber, pageSize, null, false);
    }

    public Page<Clinic> findPaged(int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        if(sort != null) {
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sort);
            else s = Sort.by(Sort.Direction.ASC, sort);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return clinicRepository.findAll(p);
    }

    public void deleteAll() {
        clinicRepository.deleteAll();
    }

    public void deleteOne(Clinic clinicToDelete) {
        clinicRepository.delete(clinicToDelete);
    }

    public List<Clinic> searchAll(LocalDate date, InterventionType type) {
        return clinicRepository.search(date, type);
    }

    public Page<Clinic> search(LocalDate date, InterventionType type, int pageNumber, int pageSize) {
        return this.search(date, type, pageNumber, pageSize, null, false);
    }

    public Page<Clinic> search(LocalDate date, InterventionType type, int pageNumber, int pageSize, String sort, boolean desc) {
        Pageable p;
        if(sort != null) {
            Sort s;
            if (desc) s = Sort.by(Sort.Direction.DESC, sort);
            else s = Sort.by(Sort.Direction.ASC, sort);
            p = PageRequest.of(--pageNumber, pageSize, s);
        } else p = PageRequest.of(--pageNumber, pageSize);
        return this.clinicRepository.search(date, type, p);
    }
}
