package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.AppointmentRequest;
import mrs.eclinicapi.model.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, String> {

}
