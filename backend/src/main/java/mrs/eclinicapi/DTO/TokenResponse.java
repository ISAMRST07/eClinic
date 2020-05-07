package mrs.eclinicapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TokenResponse {
    private String accessToken;
    private User loggedUser;
}
