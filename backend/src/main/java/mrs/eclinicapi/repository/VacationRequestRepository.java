package mrs.eclinicapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mrs.eclinicapi.model.VacationRequest;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, String> {

    @Query("select d from VacationRequest d where d.clinic.id = ?1")
    List<VacationRequest> getVacationRequestForClinic(String id);
}
