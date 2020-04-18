package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
