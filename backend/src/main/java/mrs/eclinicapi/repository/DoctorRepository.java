package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
