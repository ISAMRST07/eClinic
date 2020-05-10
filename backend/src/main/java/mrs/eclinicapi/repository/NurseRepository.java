package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Nurse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NurseRepository extends JpaRepository<Nurse, String> {

	@Query("select n from Nurse n where n.clinic.id = ?1")
	List<Nurse> getNurseForClinic(String id);
}
