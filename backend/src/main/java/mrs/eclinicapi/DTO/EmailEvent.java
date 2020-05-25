package mrs.eclinicapi.DTO;

import lombok.Getter;
import lombok.Setter;
import mrs.eclinicapi.model.UnregisteredUser;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class EmailEvent extends ApplicationEvent {
    private String subject;
    private String content;
    private String[] address;
    public EmailEvent(Object source, String subject, String content, String[] address) {
        super(source);

        this.subject = subject;
        this.content = content;
        this.address = address;
    }

}
