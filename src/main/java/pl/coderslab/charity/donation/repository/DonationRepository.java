package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;

public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
}
