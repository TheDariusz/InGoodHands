package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.entity.CategoryEntity;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;
import pl.coderslab.charity.mail.EmailService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CharityDonationService implements DonationService {

    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;
    private final DonationMapper donationMapper;
    private final CategoryService categoryService;
    private final EmailService emailService;

    @Override
    public int sumOfBags() {
        return donationRepository.sumDonationBags().orElse(0);
    }

    @Override
    public long countDonations() {
        return donationRepository.count();
    }

    @Override
    @Transactional
    public void keepDonation(DonationDto donationDto) {
        final Donation donation = donationDto.toModel();
        InstitutionEntity institutionEntity = institutionService.fetchInstitution(donation.getInstitution());

        final Set<CategoryEntity> categories = donation.getCategories().stream()
                .map(category -> categoryService.findCategoryByName(category.getName()))
                .collect(Collectors.toSet());

        DonationEntity donationEntity = donationMapper.toEntity(donation);

        donationEntity.setInstitution(institutionEntity);
        donationEntity.setCategories(categories);
        donationRepository.save(donationEntity);
        institutionEntity.addDonationEntity(donationEntity);

        sendEmailWithSummary(donationDto);

    }

    private void sendEmailWithSummary(DonationDto donationDto) {
        String subject = "Podsumowanie przekazanej darowizny";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLoggedUserEmail = authentication.getName();

        final String categories = donationDto.getCategories().stream()
                .map(c -> c.getName())
                .collect(Collectors.joining(","));

        Map<String, String> mailMessageParts = new HashMap<>();
        mailMessageParts.put("noOfBags", donationDto.getQuantity());
        mailMessageParts.put("categories", categories);
        mailMessageParts.put("institution", donationDto.getInstitution());

        if (currentLoggedUserEmail!=null) {
            emailService.sendDonationSummary(currentLoggedUserEmail, subject, mailMessageParts);
        }

    }

}
