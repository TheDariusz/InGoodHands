package pl.coderslab.charity.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

@RequiredArgsConstructor
public class CharityEmailService implements EmailService {

    private final JavaMailSender emailSender;
    private final SimpleMailMessage template;

    @Override
    public void sendDonationSummary(String to, String subject, Map<String, String> mailMessageParts) {
        String text = String.format(template.getText(),
                mailMessageParts.get("noOfBags"), mailMessageParts.get("categories"), mailMessageParts.get("institution"));
        template.setFrom("noreply@thedariusz.com");
        template.setTo(to);
        template.setSubject(subject);
        template.setText(text);
        emailSender.send(template);
    }


}
