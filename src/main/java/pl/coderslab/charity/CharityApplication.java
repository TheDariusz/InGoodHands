package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.coderslab.charity.donation.CategoryMapper;
import pl.coderslab.charity.donation.CategoryService;
import pl.coderslab.charity.donation.CharityCategoryService;
import pl.coderslab.charity.donation.CharityDonationService;
import pl.coderslab.charity.donation.DonationMapper;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.donation.InstitutionMapper;
import pl.coderslab.charity.donation.InstitutionService;
import pl.coderslab.charity.donation.InstitutionServiceImpl;
import pl.coderslab.charity.donation.repository.CategoryRepository;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.InstitutionRepository;
import pl.coderslab.charity.mail.EmailService;

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
    public DonationService donationService(DonationRepository donationRepository, InstitutionService institutionService,
            CategoryService categoryService, EmailService emailService) {
        return new CharityDonationService(donationRepository, institutionService, new DonationMapper(), categoryService, emailService);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository repository) {
        return new CharityCategoryService(repository, new CategoryMapper());
    }

}
