package mrs.eclinicapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.Diagnosis;
import mrs.eclinicapi.model.Medicine;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitDTO {
    private String anamnesis;
    private String usageMethod;
    Set<Diagnosis> diagnoses;
    Set<Medicine> medicines;
}
