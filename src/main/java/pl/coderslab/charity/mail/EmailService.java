package pl.coderslab.charity.mail;

import java.util.Map;

public interface EmailService {

    void sendDonationSummary(String to, String subject, Map<String,String> parameters);
}
