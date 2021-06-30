package pl.coderslab.charity.donation;

public interface DonationService {
    int sumOfBags();
    long countDonations();
    void keepDonation(DonationDto donationDto);
}
