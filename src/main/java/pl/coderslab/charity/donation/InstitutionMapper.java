package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.stream.Collectors;

public class InstitutionMapper {
    public Institution toModel(InstitutionEntity institutionEntity) {
        return new Institution(
                institutionEntity.getName(),
                institutionEntity.getDescription(),
                institutionEntity.getDonations().stream()
                        .map(donationEntity -> new DonationMapper().toModel(donationEntity, institutionEntity))
                        .collect(Collectors.toSet())
        );
    }

    public InstitutionEntity toEntity(Institution institution) {
        return new InstitutionEntity(
                institution.getName(),
                institution.getDescription(),
                institution.getDonations().stream()
                        .map(donation -> new DonationMapper().toEntity(donation))
                        .collect(Collectors.toSet())
        );
    }
}
