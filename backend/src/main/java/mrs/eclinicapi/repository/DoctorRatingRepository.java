package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.DoctorRating;
import mrs.eclinicapi.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRatingRepository extends JpaRepository<DoctorRating, String> {
    Optional<DoctorRating> findByDoctor_IdAndPatient_Id(String doctorID, String patientID);
}
