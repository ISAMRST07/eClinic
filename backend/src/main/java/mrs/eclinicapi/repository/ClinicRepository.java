package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	@Modifying
	@Query("UPDATE Clinic SET name = ?2 WHERE id = ?1")
	public void updateName(Long id, String newName);
	
	@Modifying
	@Query("UPDATE Clinic SET description = ?2 WHERE id = ?1")
	public void updateDescription(Long id, String newDescription);
	
	@Modifying
	@Query("UPDATE Clinic SET address = ?2 WHERE id = ?1")
	public void updateAddress(Long id, String newAddress);
	
}

