package pl.coderslab.charity.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {
    InstitutionEntity findByName(String name);
}
