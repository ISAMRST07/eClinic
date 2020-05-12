package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Intervention;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterventionRepository extends JpaRepository<Intervention, String> {

	@Query("select i from Intervention i where i.clinic.id = ?1")
	List<Intervention> getClinicIntervention(String id);
}
