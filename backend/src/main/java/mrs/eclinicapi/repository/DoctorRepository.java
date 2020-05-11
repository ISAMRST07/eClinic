package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Optional<Doctor> findDoctorByUser(User var1);

    @Query("select d from Doctor d where d.clinic.id = ?1")
    List<Doctor> getDoctorsForClinic(String id);
}
