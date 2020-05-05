package mrs.eclinicapi.DTO;

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
    private Long expiresIn;
    private User loggedUser;
}
