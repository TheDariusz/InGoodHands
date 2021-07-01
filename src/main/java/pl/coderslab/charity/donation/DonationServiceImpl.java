package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.entity.CategoryEntity;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;
    private final DonationMapper donationMapper;
    private final CategoryService categoryService;

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
    }

}
