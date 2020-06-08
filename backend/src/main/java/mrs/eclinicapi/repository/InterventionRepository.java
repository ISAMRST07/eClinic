package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterventionRepository extends JpaRepository<Intervention, String> {

    @Query("select i from Intervention i where i.clinic.id = ?1")
    List<Intervention> getClinicIntervention(String id);
    
    @Query("select i from Intervention i where i.patient.id = ?1")
    List<Intervention> getPatientIntervention(String id);
}
