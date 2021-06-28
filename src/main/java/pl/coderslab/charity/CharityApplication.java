package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.coderslab.charity.donation.CategoryMapper;
import pl.coderslab.charity.donation.CategoryService;
import pl.coderslab.charity.donation.CategoryServiceImpl;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.donation.DonationServiceImpl;
import pl.coderslab.charity.donation.InstitutionMapper;
import pl.coderslab.charity.donation.InstitutionService;
import pl.coderslab.charity.donation.InstitutionServiceImpl;
import pl.coderslab.charity.donation.repository.CategoryRepository;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.InstitutionRepository;

@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public InstitutionService institutionService(InstitutionRepository repository) {
        return new InstitutionServiceImpl(repository, new InstitutionMapper());
    }

    @Bean
    public DonationService donationService(DonationRepository repository) {
        return new DonationServiceImpl(repository);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository repository) {
        return new CategoryServiceImpl(repository, new CategoryMapper());
    }

}
