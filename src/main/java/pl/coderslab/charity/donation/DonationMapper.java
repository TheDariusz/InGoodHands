package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.CategoryEntity;
import pl.coderslab.charity.donation.repository.entity.DonationEntity;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class DonationMapper {
    public DonationEntity toEntity(Donation donation) {

        final Set<CategoryEntity> categoryEntities = donation.getCategories().stream()
                .map(c -> new CategoryMapper().toEntity(c))
                .collect(Collectors.toSet());

        final InstitutionEntity institutionEntity = new InstitutionMapper()
                .toEntity(donation.getInstitution());

        return new DonationEntity(
                donation.getQuantity(),
                categoryEntities,
                institutionEntity,
                donation.getStreet(),
                donation.getCity(),
                donation.getPhone(),
                donation.getZipCode(),
                donation.getPickUpDate(),
                donation.getPickUpTime(),
                donation.getPickUpComment()
        );
    }

    public Donation toModel(DonationEntity donationEntity) {
        final Set<Category> categories = donationEntity.getCategories().stream()
                .map(c -> new CategoryMapper().toModel(c))
                .collect(Collectors.toSet());

        final Institution institution = new InstitutionMapper().toModel(donationEntity.getInstitution());

        return new Donation(
                donationEntity.getQuantity(),
                categories,
                institution,
                donationEntity.getStreet(),
                donationEntity.getPhone(),
                donationEntity.getCity(),
                donationEntity.getZipCode(),
                donationEntity.getPickUpDate(),
                donationEntity.getPickUpTime(),
                donationEntity.getPickUpComment()
        );
    }
}
