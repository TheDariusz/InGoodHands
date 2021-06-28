package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;

import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    @Query(value = "select sum(d.quantity) from donation d")
    Optional<Integer> sumDonationBags();

}
