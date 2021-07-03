package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.coderslab.charity.mail.CharityEmailService;
import pl.coderslab.charity.mail.EmailService;

@Configuration
public class MailConfig {

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "Podsumowanie Twojej dotacji:\n" +
                        "Oddajesz %s worki z kategorii: %s\n" +
                        "dla fundacji \"%s\"");
        return message;
    }

    @Bean
    public EmailService emailService(JavaMailSender javaMailSender, SimpleMailMessage message) {
        return new CharityEmailService(javaMailSender, message);
    }
}
