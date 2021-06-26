package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.donation.repository.DonationRepository;

@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository repository;

    @Override
    public int sumOfBags() {
        return repository.sumDonationBags();
    }

    @Override
    public long countDonations() {
        return repository.count();
    }
}
