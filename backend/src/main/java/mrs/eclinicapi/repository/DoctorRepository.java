package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mrs.eclinicapi.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
