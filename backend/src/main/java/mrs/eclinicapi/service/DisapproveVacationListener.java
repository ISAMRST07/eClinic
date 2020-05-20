package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.OnDisapproveVacationCompleteEvent;
import mrs.eclinicapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DisapproveVacationListener implements
        ApplicationListener<OnDisapproveVacationCompleteEvent> {

    @Autowired
    UnregisteredUserService service;

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnDisapproveVacationCompleteEvent event) {
        this.confirmDisapproveVacation(event);
    }

    private void confirmDisapproveVacation(OnDisapproveVacationCompleteEvent event) {
    	System.out.println("confirmDisapproveVacation");
    	User user = event.getUser();
    	System.out.println("user = " + user);
    	System.out.println("reason = " + event.getReason());

        String recipientAddress = user.getEmail();
        String subject = "Vacation request declined";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setFrom(env.getProperty("spring.mail.username"));
        email.setSubject(subject);
        email.setText(user.getName() + ", your vacation request was declined." +
                "\r\n" + "Reason: " + event.getReason());
        mailSender.send(email);
    }
}
