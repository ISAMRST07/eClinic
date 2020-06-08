package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sun.awt.SunToolkit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention, String> {

    @Query("select i from Intervention i where i.clinic.id = ?1")
    List<Intervention> getClinicIntervention(String id);

    List<Intervention> findInterventionsByDoctor_IdAndPatient_Id(String doctorID, String patientID);
}
