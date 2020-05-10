package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

	@Query("select d from Doctor d where d.clinic.id = ?1")
	List<Doctor> getDoctorsForClinic(String id);
}
