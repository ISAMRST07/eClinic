package mrs.eclinicapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.Intervention;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomDTO {
    private Long id;
    private String name;
    private Long clinicId;
    private Set<Intervention> scheduledInterventions = new HashSet<>();
    private Set<Intervention> pastInterventions = new HashSet<>();
    private boolean removable = true;

}
