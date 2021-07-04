package pl.coderslab.charity.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.coderslab.charity.user.repository.entity.UserEntity;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UserEntity user;

    public OnRegistrationCompleteEvent(
            UserEntity user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
