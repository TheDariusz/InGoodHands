package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;

public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    @Query(value = "select sum(d.quantity) from donation d")
    int sumDonationBags();

}
