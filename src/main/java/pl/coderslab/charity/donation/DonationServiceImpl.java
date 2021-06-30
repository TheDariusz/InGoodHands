package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.entity.CategoryEntity;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
        InstitutionEntity institutionEntity = institutionService.fetchInstitutionByName(donationDto.getInstitution());
        DonationEntity donationEntity = MapToDonationEntity(donationDto);
        donationEntity.setInstitution(institutionEntity);
        donationRepository.save(donationEntity);
        institutionEntity.addDonationEntity(donationEntity);
    }

    private Donation MapToDonation(DonationDto donationDto) {

        return new Donation(
                Integer.parseInt(donationDto.getQuantity()),
                Set.copyOf(donationDto.getCategories()),
                donationDto.getStreet(),
                donationDto.getPhone(),
                donationDto.getCity(),
                donationDto.getZipCode(),
                LocalDate.parse(donationDto.getPickUpDate()),
                LocalTime.parse(donationDto.getPickUpTime()),
                donationDto.getPickUpComment()
        );
    }

        private DonationEntity MapToDonationEntity(DonationDto donationDto) {

            final Set<CategoryEntity> categories = donationDto.getCategories().stream()
                    .map(category -> categoryService.findCategoryByName(category.getName()))
                    .collect(Collectors.toSet());

            return new DonationEntity(
                    Integer.parseInt(donationDto.getQuantity()),
                    categories,
                    donationDto.getStreet(),
                    donationDto.getPhone(),
                    donationDto.getCity(),
                    donationDto.getZipCode(),
                    LocalDate.parse(donationDto.getPickUpDate()),
                    LocalTime.parse(donationDto.getPickUpTime()),
                    donationDto.getPickUpComment()
            );

    }
}
