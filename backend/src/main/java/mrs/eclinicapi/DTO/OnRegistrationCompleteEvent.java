package mrs.eclinicapi.DTO;

import lombok.Getter;
import lombok.Setter;
import mrs.eclinicapi.model.UnregisteredUser;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UnregisteredUser user;

    public OnRegistrationCompleteEvent(
            UnregisteredUser user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
