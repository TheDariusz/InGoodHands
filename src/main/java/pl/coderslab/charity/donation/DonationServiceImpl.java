package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;
    private final DonationMapper donationMapper;

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
        final Institution institution = institutionService.fetchInstitutionByName(donationDto.getInstitution());
        Donation donation = MapToDonation(donationDto);
        institution.getDonations().add(donation);
        institutionService.save(institution);
        final DonationEntity s = donationMapper.toEntity(donation);
        donationRepository.save(s);
    }

    private Donation MapToDonation(DonationDto donationDto) {

        final String dtoInstitution = donationDto.getInstitution();

        return new Donation(
                Integer.parseInt(donationDto.getQuantity()),
                Set.copyOf(donationDto.getCategories()),
                institutionService.fetchInstitutionByName(donationDto.getInstitution()),
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
