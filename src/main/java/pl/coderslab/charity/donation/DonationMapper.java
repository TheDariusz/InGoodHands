package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.DonationEntity;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class DonationMapper {

    public DonationEntity toEntity(Donation donation) {
        return new DonationEntity(
                donation.getQuantity(),
                donation.getStreet(),
                donation.getCity(),
                donation.getPhone(),
                donation.getZipCode(),
                donation.getPickUpDate(),
                donation.getPickUpTime(),
                donation.getPickUpComment()
        );
    }

    public Donation toModel(DonationEntity donationEntity, InstitutionEntity institutionEntity) {
        final Set<Category> categories = donationEntity.getCategories().stream()
                .map(categoryEntity -> new CategoryMapper().toModel(categoryEntity))
                .collect(Collectors.toSet());

        final Institution institution = new InstitutionMapper().toModel(institutionEntity);

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
