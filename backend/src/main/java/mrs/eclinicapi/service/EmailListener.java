package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.EmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailListener implements
        ApplicationListener<EmailEvent> {

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(EmailEvent event) {
        this.sendEmail(event);
    }

    private void sendEmail(EmailEvent event) {


        String subject = event.getSubject();
//        String confirmationUrl = "/confirmRegistration/" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(event.getAddress());
        email.setFrom(env.getProperty("spring.mail.username"));
        email.setSubject(event.getSubject());
        email.setText(event.getContent());
//        email.setText();
        mailSender.send(email);
    }
}
