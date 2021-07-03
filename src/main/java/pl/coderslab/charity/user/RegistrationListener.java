package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import pl.coderslab.charity.mail.EmailService;
import pl.coderslab.charity.user.repository.entity.UserEntity;

import java.util.UUID;

@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;
    private final MessageSource messages;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        final UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/charity/registrationConfirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        String text = message + "\r\n" + "http://localhost:8080" + confirmationUrl;
        emailService.sendRegistrationConfirmation(recipientAddress, subject, text);


    }
}
