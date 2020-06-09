package mrs.eclinicapi.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.Diagnosis;
import mrs.eclinicapi.model.Medicine;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class VisitDTO {
    private String anamnesis;
    private String usageMethod;
    Set<Diagnosis> diagnoses;
    Set<Medicine> medicines;
}
