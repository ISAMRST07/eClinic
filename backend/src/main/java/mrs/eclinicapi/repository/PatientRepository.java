package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
