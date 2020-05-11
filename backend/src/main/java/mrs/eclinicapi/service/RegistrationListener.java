package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.OnRegistrationCompleteEvent;
import mrs.eclinicapi.model.UnregisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    UnregisteredUserService service;

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UnregisteredUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        if (service.tokenExists(user)) {
            service.deleteToken(user);
        }
        service.createVerificationToken(user, token);

        String recipientAddress = user.getUser().getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/confirmRegistration/" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setFrom(env.getProperty("spring.mail.username"));
        email.setSubject(subject);
        email.setText(user.getUser().getName() + ", verify your account for eClinic" +
                "\r\n" + "http://localhost:8080" + confirmationUrl +
                "\r\n\r\nThis link is active only for 24 hours.");
        mailSender.send(email);
    }
}
