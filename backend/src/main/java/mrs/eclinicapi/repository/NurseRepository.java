package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.Nurse;
import mrs.eclinicapi.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, String> {
    Optional<Nurse> findNurseByUser (User var1);

	@Query("select n from Nurse n where n.clinic.id = ?1")
	List<Nurse> getNurseForClinic(String id);
}
