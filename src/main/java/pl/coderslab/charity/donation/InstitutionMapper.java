package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

public class InstitutionMapper {
    public Institution toModel(InstitutionEntity institutionEntity) {
        return new Institution(
                institutionEntity.getName(),
                institutionEntity.getDescription()
        );
    }

    public InstitutionEntity toEntity(Institution institution) {
        return new InstitutionEntity(
                institution.getName(),
                institution.getDescription()
        );
    }
}
