package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.coderslab.charity.donation.InstitutionMapper;
import pl.coderslab.charity.donation.InstitutionServiceImpl;
import pl.coderslab.charity.donation.repository.InstitutionRepository;

@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public InstitutionServiceImpl institutionService(InstitutionRepository repository) {
        return new InstitutionServiceImpl(repository, new InstitutionMapper());
    }

}
